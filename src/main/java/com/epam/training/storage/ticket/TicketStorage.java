package com.epam.training.storage.ticket;

import com.epam.training.model.ticket.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketStorage {

    Ticket bookTicket(Ticket ticket);

    boolean cancelTicket(long ticketId);

    List<Ticket> findAll();

    Ticket getTicketById(long ticketId);

    Map<Long, Ticket> getTickets();
}
