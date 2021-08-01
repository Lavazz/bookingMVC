package com.epam.training.dao.user;

import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.model.User;

import java.util.List;

public interface UserDao {

  User createUser(User user) throws InvalidUserException;

  User getUserById(Long id) throws InvalidUserException;

  List<User> getAllUsers();

  void updateUser(User user) throws InvalidUserException;

  void deleteUserById(Long id) throws InvalidUserException;
}
