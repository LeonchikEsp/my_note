package com.epam.mynote.controllers;

import com.epam.mynote.domain.User;
import com.epam.mynote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/user")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(value = "/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping(value = "/user/{id}")
    public boolean deleteUserById(@PathVariable("id") Long id) {
        return userService.deleteUserById(id);
    }
}
