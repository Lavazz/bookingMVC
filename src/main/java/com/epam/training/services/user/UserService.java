package com.epam.training.services.user;

import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.model.User;

import java.util.List;

public interface UserService {

  User createUser(String name, String email) throws InvalidUserException;

  User getUserById(Long id) throws InvalidUserException;

  User getUserByEmail(String email) throws InvalidUserException;

  List<User> getUsersByName(String name, int pageSize, int pageNum);
}
