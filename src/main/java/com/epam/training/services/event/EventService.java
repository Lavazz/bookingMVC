package com.epam.training.services.event;

import com.epam.training.exceptions.InvalidEventException;
import com.epam.training.model.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

  Event createEvent(String title, LocalDateTime date);

  Event getEventById(Long id) throws InvalidEventException;

  List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

  List<Event> getEventsForDay(LocalDateTime day, int pageSize, int pageNum);

  List<Event> getAllEvents();
}
