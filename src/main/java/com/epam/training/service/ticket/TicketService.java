package com.epam.training.service.ticket;

import com.epam.training.model.event.Event;
import com.epam.training.model.ticket.Ticket;
import com.epam.training.model.user.User;

import java.util.List;

public interface TicketService {

    Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

    List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

    List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

    boolean cancelTicket(long ticketId);
}
