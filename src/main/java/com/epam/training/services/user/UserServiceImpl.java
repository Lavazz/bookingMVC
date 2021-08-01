package com.epam.training.services.user;

import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.model.User;
import com.epam.training.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public User createUser(String name, String email) throws InvalidUserException {
    User user = new User(name, email);
    return userDao.createUser(user);
  }

  @Override
  public User getUserById(Long id) throws InvalidUserException {
    return userDao.getUserById(id);
  }

  public User getUserByEmail(String email) throws InvalidUserException {
    List<User> allUsers = userDao.getAllUsers();
    return allUsers.stream()
        .filter(user -> email.equalsIgnoreCase(user.getEmail()))
        .findFirst()
        .orElseThrow(() -> new InvalidUserException("User not found"));
  }

  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    List<User> allUsers = userDao.getAllUsers();
    return allUsers.stream()
        .filter(user -> name.equalsIgnoreCase(user.getName()))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }
}
