package com.epam.training.storage.user;

import com.epam.training.model.user.User;

import java.util.List;
import java.util.Map;

public interface UserStorage {

    User getUserById(long userId);

    User createUser(User user);

    User updateUser(User user);

    boolean deleteUser(long userId);

    List<User> findAll();

    void cleanStorage();

    Map<Long, User> getUsers();
}
