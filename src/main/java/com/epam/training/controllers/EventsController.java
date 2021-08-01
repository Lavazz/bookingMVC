package com.epam.training.controllers;

import com.epam.training.exceptions.InvalidEventException;
import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.facade.BookingFacade;
import com.epam.training.model.Event;
import com.epam.training.services.event.EventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/events")
public class EventsController {

  @Autowired
  private BookingFacade facade;

  @Autowired
  private EventService eventService;

  @ExceptionHandler({InvalidEventException.class, InvalidUserException.class})
  public ModelAndView handleException(HttpServletRequest req, Exception ex) {
    log.info("InvalidEventException.class");
    ModelAndView mav = new ModelAndView();
    mav.addObject("exception", ex);
    mav.addObject("message", "Events Controller Error");
    mav.addObject("url", req.getRequestURL());
    mav.setViewName("error");
    return mav;
  }

  @GetMapping("/")
  public String getEvents(ModelMap model,
      @RequestParam(required = false, defaultValue = "") String title,
      @RequestParam(required = false, defaultValue = "") String date) {
    List<Event> events;
    if (StringUtils.isNotBlank(title)) {
      events = facade.getEventsByTitle(title, 10, 0);
      model.addAttribute("message", "Events by title: " + title);
    } else if (StringUtils.isNotBlank(date)) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
      LocalDateTime dateForSearch = LocalDateTime.parse("12/02/2020 12:00", formatter);
      events = facade.getEventsForDay(dateForSearch, 10, 0);
      model.addAttribute("message", "Events by date: " + date);
    } else {
      events= facade.getAllEvents();
      model.addAttribute("message", "All Events");
    }
    model.addAttribute("events", events);
    return "events/show-events";
  }

  @GetMapping("/{id}")
  public String showEvent(ModelMap model, @PathVariable Long id) throws InvalidEventException {
    model.addAttribute("message", "Event Information");
    Event event = facade.getEventById(id);
    model.addAttribute("event", event);
    return "events/show-event";
  }

  @PostMapping("/add")
  public String createEvent(ModelMap model,
      @RequestParam String title,
      @RequestParam String date) {
    Event event = eventService.createEvent(title, parseDate(date));
    Event eventCreated = facade.createEvent(event);
    String msg = String.format("Event with id:%d, title: '%s', date: %s is created.",
        eventCreated.getId(), eventCreated.getTitle(), eventCreated.getDate().toString());
    log.info(msg);
    model.addAttribute("message", "Event created");
    model.addAttribute("event", eventCreated);
    return "events/show-event";
  }

  @PatchMapping("/{id}")
  public String updateEvent(ModelMap model,
      @PathVariable Long id,
      @RequestParam String title,
      @RequestParam String date) throws InvalidEventException {
    Event event = new Event(id, title, parseDate(date));
    facade.updateEvent(event);
    log.info(String.format("Event with id:%d is updated.", id));
    model.addAttribute("message", "Event updated");
    model.addAttribute("event", event);
    return "events/show-event";
  }

    @DeleteMapping("/{id}")
    public String deleteEvent(ModelMap model,
        @PathVariable Long id) {
      facade.deleteEventById(id);
      log.info(String.format("Event with id:%d is deleted.", id));
      model.addAttribute("message", "Event deleted");
      return "info/show-info";
    }

  private LocalDateTime parseDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    return LocalDateTime.parse(date, formatter);
  }
}
