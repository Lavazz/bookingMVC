package com.epam.training.service.event;

import com.epam.training.dao.event.EventDao;
import com.epam.training.model.event.Event;
import com.epam.training.model.event.EventImpl;
import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class EventServiceImplTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    private EventDao eventDao;

    private Event event;

 //   private static Date date;

    @Mock
    private Date date;

    List<Event> events;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
      //  date = new Date("25/07/2021");
        event = new EventImpl(1L, "Dancing Show", date);
        events= Arrays.asList(event);
    }


    @After
    public void deleteTestEvent() {
        event = null;
    }

    @Test
    public void createEvent() {

        when(eventDao.createEvent(event))
                .thenReturn(event);
       Event actual=eventService.createEvent(event);
        Assert.assertEquals(event, actual);
    }

    @Test
    public void getEventById() {
        when(eventDao.getEventById(1L))
                .thenReturn(event);
        Event eventById = eventService.getEventById(1L);
        Assert.assertEquals(eventById.getTitle(), "Dancing Show");
    }

    @Test
    public void updateEvent() {
        when(eventDao.updateEvent(new EventImpl("Dancing Show", date))).thenReturn(new EventImpl(2L, "Dancing Show", date));
        Event actual=eventService.updateEvent(new EventImpl(2L,"Dancing Show", date));
        Assert.assertEquals(2L, actual.getId());
    }

    @Test
    public void deleteEvent() {
        when(eventDao.deleteEvent(1L)).thenReturn(true);
        Assert.assertTrue(eventService.deleteEvent(1L));
    }
}