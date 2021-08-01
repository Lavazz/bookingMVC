package com.epam.training.storage;

import com.epam.training.exceptions.InvalidEventException;
import com.epam.training.exceptions.InvalidTicketException;
import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public class CommonStorageImpl implements CommonStorage {

  private static final long START_INDEX = 99;
  private final AtomicLong userCounter = new AtomicLong(START_INDEX);
  private final AtomicLong eventCounter = new AtomicLong(START_INDEX);
  private final AtomicLong ticketCounter = new AtomicLong(START_INDEX);

  private Map<Long, User> userStorage = new HashMap<>();
  private Map<Long, Event> eventStorage = new HashMap<>();
  private Map<Long, Ticket> ticketStorage = new HashMap<>();

  public void setUserStorage(Map<Long, User> userStorage) {
    this.userStorage = userStorage;
  }

  public void setEventStorage(Map<Long, Event> eventStorage) {
    this.eventStorage = eventStorage;
  }

  public void setTicketStorage(Map<Long, Ticket> ticketStorage) {
    this.ticketStorage = ticketStorage;
  }

// User

  @Override
  public User addUser(User user) throws InvalidUserException {
    Long id = userCounter.incrementAndGet();
    String email = user.getEmail();
    user.setId(id);

    boolean emailExists = userStorage.entrySet().stream()
        .anyMatch(entry -> (email.equalsIgnoreCase((entry.getValue()).getEmail())));
    if (emailExists) {
      String msg = String.format("User with email: %s already exists.", email);
      log.error(msg);
      throw new InvalidUserException(msg);
    }
    userStorage.put(id, user);
    return userStorage.get(id);
  }

  @Override
  public User getUserById(Long id) throws InvalidUserException {
    if (!userStorage.containsKey(id)) {
      String msg = String.format("Cannot find user with id: %d.", id);
      log.error(msg);
      throw new InvalidUserException(msg);
    }
    return userStorage.get(id);
  }

  @Override
  public List<User> getAllUsers() {
    return new ArrayList<>(userStorage.values());
  }

  @Override
  public void update(User user) throws InvalidUserException {
    Long id = user.getId();
    if (!userStorage.containsKey(id)) {
      String msg = String.format("Cannot update user with id: %d, user doesn't exist.", id);
      log.error(msg);
      throw new InvalidUserException(msg);
    }
    userStorage.put(id, user);
  }

  @Override
  public void removeUserById(Long id) throws InvalidUserException {
    if (!userStorage.containsKey(id)) {
      String msg = String.format("Cannot delete user with id: %d, user doesn't exist.", id);
      log.error(msg);
      throw new InvalidUserException(msg);
    }
    userStorage.remove(id);
  }

  // Event

  @Override
  public Event addEvent(Event event) {
    Long id = eventCounter.incrementAndGet();
    event.setId(id);
    eventStorage.put(id, event);
    return eventStorage.get(id);
  }

  @Override
  public Event getEventById(Long id) throws InvalidEventException {
    if (!eventStorage.containsKey(id)) {
      String msg = String.format("Cannot find the Event with id: %d.", id);
      log.error(msg);
      throw new InvalidEventException(msg);
    }
    return eventStorage.get(id);
  }

  @Override
  public List<Event> getAllEvents() {
    return new ArrayList<>(eventStorage.values());
  }

  @Override
  public void updateEvent(Event event) throws InvalidEventException {
    Long id = event.getId();
    if (!eventStorage.containsKey(id)) {
      String msg = String.format("Cannot update event with id:%d because it doesn't exist.", id);
      log.error(msg);
      throw new InvalidEventException(msg);
    }
    eventStorage.put(id, event);
  }

  @Override
  public void deleteEventById(Long id) {
    if (eventStorage.containsKey(id)) {
      eventStorage.remove(id);
    } else {
      String msg = String.format("Can't remove event with id:%d because it doesn't exist.", id);
      log.error(msg);
    }
  }

  // Ticket

  @Override
  public Ticket addTicket(Ticket ticket) {
    Long id = ticketCounter.incrementAndGet();
    ticket.setId(id);
    ticketStorage.put(id, ticket);
    return ticketStorage.get(id);
  }

  @Override
  public List<Ticket> getAllTickets() {
    return new ArrayList<>(ticketStorage.values());
  }

  @Override
  public void deleteTicketById(Long id) throws InvalidTicketException {
    if (!ticketStorage.containsKey(id)) {
      String msg = String.format("Can not delete ticket with id:%d because it doesn't exist", id);
      log.error(msg);
      throw new InvalidTicketException(msg);
    }
    ticketStorage.remove(id);
  }
}
