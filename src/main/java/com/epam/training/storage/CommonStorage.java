package com.epam.training.storage;

import com.epam.training.exceptions.InvalidEventException;
import com.epam.training.exceptions.InvalidTicketException;
import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.User;

import java.util.List;

public interface CommonStorage {

  User addUser(User user) throws InvalidUserException;

  User getUserById(Long id) throws InvalidUserException;

  List<User> getAllUsers();

  void update(User user) throws InvalidUserException;

  void removeUserById(Long id) throws InvalidUserException;

  Event addEvent(Event event);

  Event getEventById(Long id) throws InvalidEventException;

  List<Event> getAllEvents();

  void updateEvent(Event event) throws InvalidEventException;

  void deleteEventById(Long id);

  Ticket addTicket(Ticket ticket);

  List<Ticket> getAllTickets();

  void deleteTicketById(Long id) throws InvalidTicketException;
}
