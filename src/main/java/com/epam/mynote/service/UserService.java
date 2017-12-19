package com.epam.mynote.service;

import com.epam.mynote.domain.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> getAllUsers();

    boolean deleteUserById(Long id);

    User saveUser(User user);

}
