package com.epam.training.controllers;

import com.epam.training.exceptions.InvalidUserException;
import com.epam.training.facade.BookingFacade;
import com.epam.training.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

  @Autowired
  private BookingFacade facade;

  @GetMapping("/")
  public String getUser(ModelMap model,
      @RequestParam(required = false, defaultValue = "") String name,
      @RequestParam(required = false, defaultValue = "") String email) throws InvalidUserException {
    if (StringUtils.isNotEmpty(name)) {
      List<User> users = facade.getUsersByName(name, 10, 0);
      model.addAttribute("users", users);
      return "users/show-users";
    } else if (StringUtils.isNotEmpty(email)) {
      User user = facade.getUserByEmail(email);
      model.addAttribute("user", user);
      return "users/show-user";
    } else {
      model.addAttribute("message", "User not found.");
      return "info/show-info";
    }
  }

  @GetMapping("/{id}")
  public String getUserById(ModelMap model, @PathVariable Long id) throws InvalidUserException {
    User user = facade.getUserById(id);
    model.addAttribute("message", "User found by id: " + id);
    model.addAttribute("user", user);
    return "users/show-user";
  }

  @GetMapping("/add")
  public String addUser(ModelMap model,
      @RequestParam(required = false, defaultValue = "name") String name,
      @RequestParam(required = false, defaultValue = "email") String email) throws InvalidUserException {
    User user = new User(name, email);
    User createdUser = facade.createUser(user);
    model.addAttribute("message", "User created");
    model.addAttribute("user", createdUser);
    return "users/show-user";
  }

  @PatchMapping("/{id}")
  public String updateUser(ModelMap model,
      @PathVariable Long id,
      @RequestParam String userName,
      @RequestParam String userEmail) throws InvalidUserException {
    User user = new User(id, userName, userEmail);
    facade.updateUser(user);
    model.addAttribute("message", "User is updated");
    return "users/show-users";
  }

  @DeleteMapping("/{id}")
  public String deleteUser(ModelMap model, @PathVariable Long id) throws InvalidUserException {
    facade.deleteUserById(id);
    model.addAttribute("message", "User deleted");
    return "info/show-info";
  }
}
