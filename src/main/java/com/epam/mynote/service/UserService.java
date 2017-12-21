package com.epam.mynote.service;

import com.epam.mynote.domain.User;

import java.util.List;
import javax.transaction.Transactional;

@Transactional
public interface UserService {

    User getUserById(Long id);

    List<User> getAllUsers();

    Integer deleteUserById(Long id);

    User saveUser(User user);

}
