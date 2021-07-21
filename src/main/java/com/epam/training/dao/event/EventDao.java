package com.epam.training.dao.event;

import com.epam.training.model.event.Event;

import java.util.List;

public interface EventDao {

    Event getEventById(long eventId);

    Event createEvent(Event event);

    Event updateEvent(Event event);

    boolean deleteEvent(long eventId);

    List<Event> findAll();

}
