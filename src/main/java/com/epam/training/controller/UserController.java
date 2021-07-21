package com.epam.training.controller;

import com.epam.training.facade.BookingFacade;
import com.epam.training.model.user.User;
import com.epam.training.model.user.UserImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BookingFacade bookingFacade;

    @PostMapping
    public ModelAndView createUser(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String email) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = new UserImpl(name, email);

        modelAndView.addObject("entities", user);
        modelAndView.addObject("message", "create entities");
        return modelAndView;
    }

    @PutMapping("/{id}")
    public ModelAndView updateUser(@PathVariable long id,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String email) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = bookingFacade.getUserById(id);
        if (Objects.nonNull(user)) {
            user.setName(name);
            user.setEmail(email);
            user = bookingFacade.updateUser(user);
            modelAndView.addObject("entities", user);
            modelAndView.addObject("message", "update entities");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteUser(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        boolean isDeleted = bookingFacade.deleteUser(id);
        if (isDeleted) {
            modelAndView.addObject("message", "delete entities");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getUserById(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = bookingFacade.getUserById(id);
        if (Objects.nonNull(user)) {
            modelAndView.addObject("entities", user);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/name/{name}")
    public ModelAndView getUsersByName(@PathVariable String name,
                                       @RequestParam(required = false, defaultValue = "100") int pageSize,
                                       @RequestParam(required = false, defaultValue = "1") int pageNum) {
        ModelAndView modelAndView = new ModelAndView("entities");
        List<User> users = bookingFacade.getUsersByName(name, pageSize, pageNum);
        if (!users.isEmpty()) {
            modelAndView.addObject("entities", users);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }

    @GetMapping("/email/{email}")
    public ModelAndView getUsersByEmail(@PathVariable String email) {
        ModelAndView modelAndView = new ModelAndView("entities");
        User user = bookingFacade.getUserByEmail(email);
        if (Objects.nonNull(user)) {
            modelAndView.addObject("entities", user);
            modelAndView.addObject("message", "found entity");
        } else {
            modelAndView.addObject("message", "not found entity");
        }
        return modelAndView;
    }
}

