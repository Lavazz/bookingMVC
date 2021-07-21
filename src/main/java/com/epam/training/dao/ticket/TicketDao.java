package com.epam.training.dao.ticket;

import com.epam.training.model.ticket.Ticket;

import java.util.List;

public interface TicketDao {

    Ticket bookTicket(Ticket ticket);

    boolean cancelTicket(long ticketId);

    List<Ticket> findAll();

    Ticket getTicketById(long ticketId);

}
