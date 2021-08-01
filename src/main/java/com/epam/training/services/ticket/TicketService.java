package com.epam.training.services.ticket;

import com.epam.training.exceptions.InvalidTicketException;
import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.Ticket.Category;
import com.epam.training.model.User;

import java.util.List;

public interface TicketService {

  Ticket bookTicket(Long userId, Long eventId, int place, Category category);

  List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

  List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

  List<Ticket> getAllTickets();

  void cancelTicket(Long ticketId) throws InvalidTicketException;
}
