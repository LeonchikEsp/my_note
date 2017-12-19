package com.epam.mynote.repository;

import com.epam.mynote.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    List<User> findAll();

    boolean deleteUserById(Long id);
}
