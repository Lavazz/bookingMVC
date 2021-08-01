package com.epam.tickets.integration;

import static org.junit.Assert.assertEquals;

import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.facade.BookingFacadeImpl;
import com.epam.training.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsersTest {

  BookingFacadeImpl facade;

  @Before
  public void shouldAnswerWithTrue() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-context.xml");
    facade = (BookingFacadeImpl) ctx.getBean("facade");
  }

  @Test
  public void createNewUserTest() throws InvalidUserException {
    User user = new User("Alex", "alex@gmail.com");
    User createdUser = facade.createUser(user);

    assertEquals(createdUser.getName(), "Alex");
    assertEquals(createdUser.getEmail(), "alex@gmail.com");
  }

  @Test
  public void getUserByEmailTest() throws InvalidUserException {
    User user = new User("John Foo", "jf@gmail.com");
    facade.createUser(user);
    User userByEmail = facade.getUserByEmail("jf@gmail.com");

    assertEquals(userByEmail.getEmail(), "jf@gmail.com");
    assertEquals(userByEmail.getName(), "John Foo");
  }
}
