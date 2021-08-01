package com.epam.training.facade;

import com.epam.training.exceptions.InvalidEventException;
import com.epam.training.exceptions.InvalidTicketException;
import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.model.Event;
import com.epam.training.model.Ticket;
import com.epam.training.model.Ticket.Category;
import com.epam.training.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingFacade {

  /**
   * Gets event by its id.
   *
   * @return Event.
   */
  Event getEventById(Long eventId) throws InvalidEventException;

  /**
   * Get list of events by matching title. Title is matched using 'contains' approach. In case
   * nothing was found, empty list is returned.
   *
   * @param title    Event title or it's part.
   * @param pageSize Pagination param. Number of events to return on a page.
   * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
   * @return List of events.
   */
  List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

  /**
   * Get list of events for specified day. In case nothing was found, empty list is returned.
   *
   * @param day      LocalDateTime object from which day information is extracted.
   * @param pageSize Pagination param. Number of events to return on a page.
   * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
   * @return List of events.
   */
  List<Event> getEventsForDay(LocalDateTime day, int pageSize, int pageNum);

  /**
   * Creates new event. Event id should be auto-generated.
   *
   * @param event Event data.
   * @return Created Event object.
   */
  Event createEvent(Event event);

  /**
   * Updates event using given data.
   *
   * @param event Event data for update. Should have id set.
   */
  void updateEvent(Event event) throws InvalidEventException;

  /**
   * Deletes event by its id.
   *
   * @param eventId Event id.
   */
  void deleteEventById(Long eventId);

  /**
   * Gets user by its id.
   *
   * @return User.
   */
  User getUserById(Long userId) throws InvalidUserException;

  /**
   * Gets user by its email. Email is strictly matched.
   *
   * @return User.
   */
  User getUserByEmail(String email) throws InvalidUserException;

  /**
   * Get list of users by matching name. Name is matched using 'contains' approach. In case nothing
   * was found, empty list is returned.
   *
   * @param name     Users name or it's part.
   * @param pageSize Pagination param. Number of users to return on a page.
   * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
   * @return List of users.
   */
  List<User> getUsersByName(String name, int pageSize, int pageNum);

  /**
   * Creates new user. User id should be auto-generated.
   *
   * @param user User data.
   * @return Created User object.
   */
  User createUser(User user) throws InvalidUserException;

  /**
   * Updates user using given data.
   *
   * @param user User data for update. Should have id set.
   */
  void updateUser(User user) throws InvalidUserException;

  /**
   * Deletes user by its id.
   *
   * @param userId User id.
   */
  void deleteUserById(Long userId) throws InvalidUserException;

  /**
   * Book ticket for a specified event on behalf of specified user.
   *
   * @param userId   User Id.
   * @param eventId  Event Id.
   * @param place    Place number.
   * @param category Service category.
   * @return Booked ticket object.
   * @throws IllegalStateException if this place has already been booked.
   */
  Ticket bookTicket(Long userId, Long eventId, int place, Category category);

  /**
   * Get all booked tickets for specified user. Tickets should be sorted by event date in descending
   * order.
   *
   * @param user     User
   * @param pageSize Pagination param. Number of tickets to return on a page.
   * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
   * @return List of Ticket objects.
   */
  List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

  /**
   * Get all booked tickets for specified event. Tickets should be sorted in by user email in
   * ascending order.
   *
   * @param event    Event
   * @param pageSize Pagination param. Number of tickets to return on a page.
   * @param pageNum  Pagination param. Number of the page to return. Starts from 1.
   * @return List of Ticket objects.
   */
  List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

  /**
   * Cancel ticket with a specified id.
   *
   * @param ticketId Ticket id.
   */
  void cancelTicket(Long ticketId) throws InvalidTicketException;

  /**
   * Get all available events
   * @return List of events
   */
  List<Event> getAllEvents();
}
