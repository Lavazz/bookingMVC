package com.epam.training.dao.event;

import com.epam.training.model.event.Event;
import com.epam.training.model.event.EventImpl;
import com.epam.training.storage.event.EventStorage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class EventDaoImplTest {

    @InjectMocks
    private EventDaoImpl eventDao;

    @Mock
    private EventStorage eventStorage;

    private Event event;

    @Mock
    private Date date;


    // private static Date date;
    List<Event> events;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
      //  date = new Date("25/07/2021");
        event = new EventImpl(1L, "Dancing Show", date);
        events = Arrays.asList(event);
    }


    @Test
    public void createEvent() {
        when(eventStorage.createEvent(event))
                .thenReturn(event);
        Event actual = eventDao.createEvent(event);
        Assert.assertEquals(event, actual);
    }

    @Test
    public void getEventById() {
        when(eventStorage.getEventById(1L))
                .thenReturn(event);
        Event eventById = eventDao.getEventById(1L);
        Assert.assertEquals(eventById.getTitle(), "Dancing Show");
    }


}