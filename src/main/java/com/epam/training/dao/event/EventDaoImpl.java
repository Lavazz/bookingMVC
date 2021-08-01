package com.epam.training.dao.event;

import com.epam.training.exceptions.InvalidEventException;
import com.epam.training.model.Event;
import com.epam.training.storage.CommonStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {


  private final CommonStorage commonStorage;

  @Autowired
  public EventDaoImpl(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  @Override
  public Event createEvent(Event event) {
    return commonStorage.addEvent(event);
  }

  @Override
  public Event getEventById(Long id) throws InvalidEventException {
    return commonStorage.getEventById(id);
  }

  @Override
  public void updateEvent(Event event) throws InvalidEventException {
    commonStorage.updateEvent(event);
  }

  @Override
  public List<Event> getAllEvents() {
    return commonStorage.getAllEvents();
  }

  @Override
  public void deleteEventById(Long id) {
    commonStorage.deleteEventById(id);
  }
}
