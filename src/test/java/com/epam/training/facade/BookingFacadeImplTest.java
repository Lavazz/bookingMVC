package com.epam.training.facade;


import com.epam.training.model.event.Event;
import com.epam.training.model.event.EventImpl;
import com.epam.training.model.ticket.Ticket;
import com.epam.training.model.user.User;
import com.epam.training.model.user.UserImpl;
import com.epam.training.storage.user.UserStorage;
import com.epam.training.storage.user.UserStorageImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.training.model.ticket.Ticket.Category.*;


public class BookingFacadeImplTest {

    private ApplicationContext context;
    private UserStorage bookingStorage;
    private BookingFacade bookingFacade;

    @Before
    public void setUp() throws BeansException {

        context = new ClassPathXmlApplicationContext("spring.xml");
        bookingStorage = context.getBean(UserStorageImpl.class);
        bookingFacade = context.getBean("bookingFacade", BookingFacadeImpl.class);

    }

    @Test
    public void testIntegrationScenario() {
        User userAlfred = bookingFacade.createUser(new UserImpl("Alfred", "alfred@gmail.com"));
        User userRobert = bookingFacade.createUser(new UserImpl("Robert", "robert@gmail.com"));
        User userWilliam = bookingFacade.createUser(new UserImpl("William", "william@gmail.com"));

        Event event1 = bookingFacade.createEvent(new EventImpl("Dancing Show", new Date()));

        Ticket ticket1 = bookingFacade.bookTicket(userAlfred.getId(), event1.getId(), 1, STANDARD);
        Ticket ticket2 = bookingFacade.bookTicket(userAlfred.getId(), event1.getId(), 2, PREMIUM);
        Ticket ticket3 = bookingFacade.bookTicket(userAlfred.getId(), event1.getId(), 3, BAR);

        Ticket ticket4 = bookingFacade.bookTicket(userRobert.getId(), event1.getId(), 4, STANDARD);
        Ticket ticket5 = bookingFacade.bookTicket(userRobert.getId(), event1.getId(), 5, PREMIUM);
        Ticket ticket6 = bookingFacade.bookTicket(userRobert.getId(), event1.getId(), 6, BAR);

        Ticket ticket7 = bookingFacade.bookTicket(userWilliam.getId(), event1.getId(), 7, STANDARD);
        Ticket ticket8 = bookingFacade.bookTicket(userWilliam.getId(), event1.getId(), 8, PREMIUM);
        Ticket ticket9 = bookingFacade.bookTicket(userWilliam.getId(), event1.getId(), 9, BAR);

        List<Ticket> expectedTicketsByUser = Arrays.asList(ticket1, ticket2, ticket3);

        List<Ticket> expectedTicketsByUserSort = expectedTicketsByUser.stream()
                .sorted(Comparator.comparingLong(Ticket::getId))
                .collect(Collectors.toList());


        List<Ticket> actualTicketsByUser = bookingFacade.getBookedTickets(userAlfred, 1, 1);

        List<Ticket> expectedTicketsByEvent =
                Arrays.asList(
                        ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, ticket7, ticket8, ticket9);

        List<Ticket> expectedTicketsByEventSort = expectedTicketsByEvent.stream()
                .sorted(Comparator.comparingLong(Ticket::getId))
                .collect(Collectors.toList());

        List<Ticket> actualTicketsByEvent = bookingFacade.getBookedTickets(event1, 1, 1);

        Assert.assertEquals(expectedTicketsByUserSort, actualTicketsByUser);
        Assert.assertEquals(expectedTicketsByEventSort, actualTicketsByEvent);
        Assert.assertTrue(bookingFacade.cancelTicket(ticket3.getId()));
        Assert.assertTrue(bookingFacade.cancelTicket(ticket2.getId()));
        Assert.assertTrue(bookingFacade.cancelTicket(ticket1.getId()));
    }

    @After
    public void cleanUp() {
        bookingStorage.cleanStorage();
    }
}
