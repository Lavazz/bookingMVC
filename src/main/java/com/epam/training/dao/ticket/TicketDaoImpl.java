package com.epam.training.dao.ticket;

import com.epam.training.exceptions.InvalidTicketException;
import com.epam.training.model.Ticket;
import com.epam.training.dao.ticket.TicketDao;
import com.epam.training.storage.CommonStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

  private final CommonStorage commonStorage;

  @Autowired
  public TicketDaoImpl(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  @Override
  public List<Ticket> getAllTickets() {
    return commonStorage.getAllTickets();
  }

  @Override
  public Ticket addTicket(Ticket ticket) {
    return commonStorage.addTicket(ticket);
  }

  @Override
  public void deleteTicketById(Long id) throws InvalidTicketException {
    commonStorage.deleteTicketById(id);
  }
}
