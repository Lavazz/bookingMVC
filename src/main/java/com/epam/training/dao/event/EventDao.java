package com.epam.training.dao.event;

import com.epam.training.exceptions.InvalidEventException;
import com.epam.training.model.Event;

import java.util.List;

public interface EventDao {

  Event createEvent(Event event);

  Event getEventById(Long id) throws InvalidEventException;

  List<Event> getAllEvents();

  void updateEvent(Event event) throws InvalidEventException;

  void deleteEventById(Long id);
}
