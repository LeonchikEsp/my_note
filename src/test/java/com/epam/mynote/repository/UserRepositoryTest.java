package com.epam.mynote.repository;

import com.epam.mynote.domain.Note;
import com.epam.mynote.domain.Notebook;
import com.epam.mynote.domain.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenGetUserById_thenReturnUser() {
        User user = new User(1L, "test_guy", null, null);

        User attachedTestUser = entityManager.merge(user);
        entityManager.flush();

        User foundUser = userRepository.findUserById(attachedTestUser.getId());

        Assertions.assertThat(foundUser).isEqualTo(attachedTestUser);
        entityManager.remove(attachedTestUser);

    }

    @Test
    public void whenDeleteUserById_thenReturnOne() {
        User user = new User(1L, "test_guy", null, null);

        User attachedTestUser = entityManager.merge(user);
        entityManager.flush();

        Integer deleteResult = userRepository.deleteUserById(attachedTestUser.getId());

        Assertions.assertThat(deleteResult).isEqualTo(1);
        entityManager.remove(attachedTestUser);
    }

    @Test
    public void whenSaveUser_thenReturnUser() {
        User user = new User(1L, "test_guy", null, null);

        User attachedTestUser = entityManager.merge(user);
        entityManager.flush();

        attachedTestUser.setName("updated_name");
        User updatedUser = userRepository.save(attachedTestUser);

        Assertions.assertThat(attachedTestUser.getName()).isEqualTo(updatedUser.getName());
    }

    @Test
    public void whenFindAllUsers_thenReturnListOfUsers() {
        User user1 = new User(1L, "test_guy", null, null);
        User user2 = new User(2L, "test_guy2", null, null);

        User attachedTestUser = entityManager.merge(user1);
        User attachedTestUser2 = entityManager.merge(user2);
        entityManager.flush();

        List<User> userList = userRepository.findAll();
        List<User> expectedUserList = Arrays.asList(attachedTestUser2, attachedTestUser);
        Assertions.assertThat(userList.size()).isEqualTo(expectedUserList.size());
        Assertions.assertThat(userList).containsExactlyInAnyOrder(
                expectedUserList.toArray(new User[expectedUserList.size()])
        );

    }

}
