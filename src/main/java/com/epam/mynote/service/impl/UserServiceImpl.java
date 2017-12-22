package com.epam.mynote.service.impl;

import com.epam.mynote.aspect.LogForExecutionTime;
import com.epam.mynote.domain.User;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.UserService;
import com.epam.mynote.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        if (!Validator.validId(id))
            return null;
        return userRepository.findUserById(id);
    }

    @LogForExecutionTime
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @LogForExecutionTime
    @Override
    public Integer deleteUserById(Long id) {
        if (!Validator.validId(id))
            return null;
        return userRepository.deleteUserById(id);
    }

    @LogForExecutionTime
    @Override
    public User saveUser(User user) {
        if (!Validator.validUser(user))
            return null;
        return userRepository.save(user);
    }
}
