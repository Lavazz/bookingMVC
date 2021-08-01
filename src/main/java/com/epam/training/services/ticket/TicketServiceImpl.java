package com.epam.training.services.ticket;

import com.epam.training.exceptions.InvalidTicketException;
import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.Ticket.Category;
import com.epam.training.model.User;
import com.epam.training.dao.ticket.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

  @Autowired
  private TicketDao ticketDao;

  public Ticket bookTicket(Long userId, Long eventId, int place, Category category) {
    Ticket ticket = new Ticket(userId, eventId, place, category);
    return ticketDao.addTicket(ticket);
  }

  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    List<Ticket> allTickets = ticketDao.getAllTickets();
    Long lookedUserId = user.getId();
    return allTickets.stream()
        .filter(ticket -> Objects.nonNull(ticket.getUserId()))
        .filter(ticket -> ticket.getUserId().equals(lookedUserId))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    List<Ticket> allTickets = ticketDao.getAllTickets();
    Long lookedEventId = event.getId();
    return allTickets.stream()
        .filter(ticket -> Objects.nonNull(ticket.getEventId()))
        .filter(ticket -> ticket.getEventId().equals(lookedEventId))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  @Override
  public List<Ticket> getAllTickets() {
    return ticketDao.getAllTickets();
  }

  @Override
  public void cancelTicket(Long ticketId) throws InvalidTicketException {
    ticketDao.deleteTicketById(ticketId);
  }

  public void setTicketDao(TicketDao ticketDao) {
    this.ticketDao = ticketDao;
  }
}
