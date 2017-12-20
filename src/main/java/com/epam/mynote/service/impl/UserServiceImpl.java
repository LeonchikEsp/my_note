package com.epam.mynote.service.impl;

import com.epam.mynote.domain.User;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.UserService;
import com.epam.mynote.util.Validator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        if (!Validator.validId(id))
            return null;
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Integer deleteUserById(Long id) {
        if (!Validator.validId(id))
            return 0;
        return userRepository.deleteUserById(id);
    }

    @Override
    public User saveUser(User user) {
        if (!Validator.validUser(user))
            return null;
        return userRepository.save(user);
    }
}
