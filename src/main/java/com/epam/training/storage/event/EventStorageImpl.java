package com.epam.training.storage.event;

import com.epam.training.model.event.Event;
import com.epam.training.model.event.EventImpl;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventStorageImpl implements EventStorage {

    private final Map<Long, Event> eventMap;

    public EventStorageImpl() {
        eventMap = new HashMap<>();
    }

    @Override
    public Event getEventById(long eventId) {
        return eventMap.get(eventId);
    }

    @Override
    public Event createEvent(Event event) {
        return eventMap.put(event.getId(), event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventMap.replace(event.getId(), event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventMap.remove(eventId) != null;
    }

    @Override
    public List<Event> findAllEvents() {
      //  return new ArrayList<>(eventMap.values());
List<Event> events=new ArrayList<>();
events.add(new EventImpl("Disco", new Date("07/07/2021")));
events.add(new EventImpl("Cartoon", new Date("07/07/2021")));
events.add(new EventImpl("Movie", new Date("07/07/2021")));
return events;

    }

    @Override
    public Map<Long, Event> getEvents() {
        return eventMap;
    }


}
