package com.epam.training.dao.ticket;

import com.epam.training.exceptions.InvalidTicketException;
import com.epam.training.model.Ticket;

import java.util.List;

public interface TicketDao {

  Ticket addTicket(Ticket ticket);

  List<Ticket> getAllTickets();

  void deleteTicketById(Long id) throws InvalidTicketException;
}
