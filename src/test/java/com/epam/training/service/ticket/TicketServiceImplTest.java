package com.epam.training.service.ticket;

import com.epam.training.dao.ticket.TicketDaoImpl;
import com.epam.training.model.ticket.Ticket;
import com.epam.training.model.ticket.TicketImpl;
import com.epam.training.model.user.User;
import com.epam.training.model.user.UserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class TicketServiceImplTest {

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Mock
    private TicketDaoImpl ticketDao;

    private Ticket ticket;
    private List<Ticket> tickets;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ticket = new TicketImpl(1L, 1L, 1, Ticket.Category.BAR);
        tickets.add(ticket);
    }


    @Test
    public void bookTicket() {
        when(ticketDao.bookTicket(ticket)).thenReturn(ticket);
        Ticket actualTicket = ticketService.bookTicket(1L, 1L, 1, Ticket.Category.BAR);
        Assert.assertEquals(ticket, actualTicket);
    }

    @Test
    public void getBookedTickets() {
        when(ticketDao.findAll())
                .thenReturn(tickets);
        List<Ticket> actualTicket = ticketService.getBookedTickets(new UserImpl( 1L, "Kate", "email@gmail.com"), 1, 1);
        Assert.assertEquals(tickets, actualTicket);
    }

    @Test
    public void testGetBookedTickets() {
    }

    @Test
    public void cancelTicket() {
    }
}