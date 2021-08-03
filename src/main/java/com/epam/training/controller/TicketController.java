package com.epam.training.controller;

import com.epam.training.facade.BookingFacade;
import com.epam.training.model.event.Event;
import com.epam.training.model.ticket.Ticket;
import com.epam.training.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/allTickets")
    public String getAllTickets(Model model) {
        log.debug("get all tickets");
        List<Ticket> allTickets = bookingFacade.getAllTickets();
        model.addAttribute("allTickets", allTickets);
        model.addAttribute("heading", "List of all tickets in DB");
        log.info("Method start. UserController.");
        return "list_tickets";
    }

    @PostMapping("/new")
    public ModelAndView bookTicket(@RequestParam long userId,
                                   @RequestParam long eventId,
                                   @RequestParam int place,
                                   @RequestParam Ticket.Category category) {
        ModelAndView modelAndView = new ModelAndView("entities");
        if (Objects.nonNull(bookingFacade.getUserById(userId)) && Objects.nonNull(bookingFacade.getEventById(eventId))) {
            Ticket ticket = bookingFacade.bookTicket(userId, eventId, place, category);
            modelAndView.addObject("entities", ticket);
            modelAndView.addObject("message", "ticket booked");
        } else {
            modelAndView.addObject("message", "ticket not booked");
        }
        return modelAndView;
    }

    @GetMapping(value = "/{entity}/{id}")
    public ModelAndView getBookedTickets(@PathVariable long id,
                                         @PathVariable String entity,
                                         @RequestParam(required = false, defaultValue = "100") int pageSize,
                                         @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<Ticket> tickets = getTickets(id, entity, pageSize, pageNum);
        if (!tickets.isEmpty()) {
            modelAndView.addObject("entities", tickets);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView cancelTicket(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        boolean isDeleted = bookingFacade.cancelTicket(id);
        if (isDeleted) {
            modelAndView.addObject("message", "delete entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    private List<Ticket> getTickets(long id, String entity, int pageSize, int pageNum) {
        List<Ticket> tickets = new ArrayList<>();
        if ("users".equalsIgnoreCase(entity)) {
            User user = bookingFacade.getUserById(id);
            tickets = bookingFacade.getBookedTickets(user, pageSize, pageNum);
        }
        if ("events".equalsIgnoreCase(entity)) {
            Event event = bookingFacade.getEventById(id);
            tickets = bookingFacade.getBookedTickets(event, pageSize, pageNum);
        }
        return tickets;
    }

}
