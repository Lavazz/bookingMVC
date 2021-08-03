package com.epam.training.storage.event;

import com.epam.training.model.event.Event;

import java.util.List;
import java.util.Map;

public interface EventStorage {

    Event getEventById(long eventId);

    Event createEvent(Event event);

    Event updateEvent(Event event);

    boolean deleteEvent(long eventId);

    List<Event> findAllEvents();

    Map<Long, Event> getEvents();
}
