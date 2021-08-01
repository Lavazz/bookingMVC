package com.epam.tickets.integration;

import static com.epam.training.model.Ticket.Category.PREMIUM;
import static org.junit.Assert.assertEquals;

import com.epam.training.facade.BookingFacadeImpl;
import com.epam.training.model.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TicketTest {

  BookingFacadeImpl facade;

  @Before
  public void shouldAnswerWithTrue() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-context.xml");
    facade = (BookingFacadeImpl) ctx.getBean("facade");
  }

  @Test
  public void createNewTicketTest() {
    Ticket ticket = facade.bookTicket(2L, 3L, 10, PREMIUM);

    assertEquals(Long.valueOf(2), ticket.getEventId());
    assertEquals(Long.valueOf(3), ticket.getUserId());
    assertEquals(PREMIUM, ticket.getCategory());
  }
}
