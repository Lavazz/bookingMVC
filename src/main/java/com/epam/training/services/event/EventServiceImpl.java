package com.epam.training.services.event;

import com.epam.training.exceptions.InvalidEventException;
import com.epam.training.model.Event;
import com.epam.training.dao.event.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventDao eventDao;

  @Override
  public Event createEvent(String title, LocalDateTime date) {
    Event event = new Event(title, date);
    return eventDao.createEvent(event);
  }

  @Override
  public Event getEventById(Long id) throws InvalidEventException {
    return eventDao.getEventById(id);
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    List<Event> allEvents = eventDao.getAllEvents();
    return allEvents.stream()
        .filter(event -> title.equalsIgnoreCase(event.getTitle()))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  @Override
  public List<Event> getEventsForDay(LocalDateTime day, int pageSize, int pageNum) {
    List<Event> allEvents = eventDao.getAllEvents();
    return allEvents.stream()
        .filter(event -> day.equals(event.getDate()))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  @Override
  public List<Event> getAllEvents() {
    return eventDao.getAllEvents();
  }

  public void setEventDao(EventDao eventDao) {
    this.eventDao = eventDao;
  }
}
