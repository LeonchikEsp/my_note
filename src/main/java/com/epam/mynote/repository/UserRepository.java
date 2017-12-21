package com.epam.mynote.repository;

import com.epam.mynote.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    @Override
    List<User> findAll();

    Integer deleteUserById(Long id);

}
