package com.epam.training.dao.user;

import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.model.User;
import com.epam.training.dao.user.UserDao;
import com.epam.training.storage.CommonStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

  private final CommonStorage commonStorage;

  @Autowired
  public UserDaoImpl(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  @Override
  public User createUser(User user) throws InvalidUserException {
    return commonStorage.addUser(user);
  }

  @Override
  public User getUserById(Long id) throws InvalidUserException {
    return commonStorage.getUserById(id);
  }

  @Override
  public List<User> getAllUsers() {
    return commonStorage.getAllUsers();
  }

  @Override
  public void updateUser(User user) throws InvalidUserException {
    commonStorage.update(user);
  }

  @Override
  public void deleteUserById(Long id) throws InvalidUserException {
    commonStorage.removeUserById(id);
  }
}
