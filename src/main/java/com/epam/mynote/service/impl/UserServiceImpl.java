package com.epam.mynote.service.impl;

import com.epam.mynote.domain.User;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.UserService;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User getUser(Long id){
        return userRepository.getOne(id);
    }

    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    User saveUser(User user){
        return userRepository.save(user);
    }

}
