package com.epam.training.controller;

import com.epam.training.facade.BookingFacade;
import com.epam.training.model.event.Event;
import com.epam.training.model.event.EventImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/allEvents")
    public String getAllTickets(Model model) {
        log.debug("Get all tickets");
        List<Event> allEvents = bookingFacade.getAllEvents();
        model.addAttribute("allEvents", allEvents);
        model.addAttribute("heading", "List of all events in DB");
        return "list_events";
    }


    @PostMapping("/new")
    public ModelAndView createEvent(@RequestParam(required = false) String title,
                                    @RequestParam(required = false) String date) {
        ModelAndView modelAndView = new ModelAndView("entities");
        Event event = new EventImpl(title, parseDate(date));
        modelAndView.addObject("entities", event);
        modelAndView.addObject("message", "create entities");
        return modelAndView;
    }

    @PutMapping("/{id}")
    public ModelAndView updateEvent(@PathVariable long id,
                                    @RequestParam(required = false) String title,
                                    @RequestParam(required = false) String date) {
        ModelAndView modelAndView = new ModelAndView("entities");
        Event event = bookingFacade.getEventById(id);
        if (Objects.nonNull(event)) {
            event.setTitle(title);
            event.setDate(parseDate(date));
            event = bookingFacade.updateEvent(event);
            modelAndView.addObject("entities", event);
            modelAndView.addObject("message", "update entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteEvent(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        boolean isDeleted = bookingFacade.deleteEvent(id);
        if (isDeleted) {
            modelAndView.addObject("message", "delete entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getEventById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        Event event = bookingFacade.getEventById(id);
        if (Objects.nonNull(event)) {
            modelAndView.addObject("entities", event);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/title/{title}")
    public ModelAndView getEventsByTitle(@PathVariable String title,
                                         @RequestParam(required = false, defaultValue = "100") int pageSize,
                                         @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<Event> events = bookingFacade.getEventsByTitle(title, pageSize, pageNum);
        if (!events.isEmpty()) {
            modelAndView.addObject("entities", events);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/date/{date}")
    public ModelAndView getEventsByDate(@PathVariable String date,
                                        @RequestParam(required = false, defaultValue = "100") int pageSize,
                                        @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<Event> events = bookingFacade.getEventsForDay(parseDate(date), pageSize, pageNum);
        if (!events.isEmpty()) {
            modelAndView.addObject("entities", events);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    private Date parseDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}
