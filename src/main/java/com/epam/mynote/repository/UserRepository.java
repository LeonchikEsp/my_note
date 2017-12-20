package com.epam.mynote.repository;

import com.epam.mynote.domain.User;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserById(Long id);

    @Override
    List<User> findAll();

    Integer deleteUserById(Long id);

}
