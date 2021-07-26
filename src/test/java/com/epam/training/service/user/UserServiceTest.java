package com.epam.training.service.user;

import com.epam.training.dao.user.UserDao;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserDao userDao;

    private User user;

    private static Date date;
    List<User> users;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        date = new Date();
        user = new UserImpl(1L, "Kate", "kate@email.com");
        users = new ArrayList<>();
        users.add(user);
    }

    @Test
    public void getUserByIdTest() {
        when(userDao.getUserById(1L))
                .thenReturn(user);
        User userById = userService.getUserById(1L);
        Assert.assertEquals(userById, user);
    }

    @Test(expected = IllegalStateException.class)
    public void getNotExistingUserByIdTest() {
        userService.getUserById(1000L);
    }


    @Test(expected = IllegalStateException.class)
    public void getNotExistingUserByEmailTest() {
        userService.getUserByEmail("test@gmail.com");
    }

    @Test
    public void getUsersByNameTest() {
        when(userDao.findAll())
                .thenReturn(users);
        List<User> actualUser = userService.getUsersByName("Kate", 1, 1);
        Assert.assertEquals(users, actualUser);
    }

    @Test
    public void updateUserTest() {
        User newUser = new UserImpl(1L, "NewUser", "newEmail@gmail.com");
        when(userDao.updateUser(newUser)).thenReturn(newUser);
        User actual = userService.updateUser(newUser);
        Assert.assertEquals(newUser, actual);
    }

}