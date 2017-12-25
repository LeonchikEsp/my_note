package com.epam.mynote.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.epam.mynote.domain.User;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User baseUser = new User(1L, "test_guy", null, null);

        Mockito.when(userRepository.findUserById(baseUser.getId()))
            .thenReturn(baseUser);
        Mockito.when(userRepository.deleteUserById(baseUser.getId()))
            .thenReturn(1);
        Mockito.when(userRepository.save(baseUser))
            .thenReturn(baseUser);
    }

    @Test
    public void whenValidUser_thenUserShouldBeFound() {
        User testUser = new User(1L, "test_guy", null, null);

        User userFound = userService.getUserById(testUser.getId());

        assertThat(userFound)
            .isEqualTo(testUser);
    }

    @Test
    public void whenInvalidUserId_thenNullShouldBeReturned() {
        User testUser = new User(-5L, "test_guy", null, null);

        User userFound = userService.getUserById(testUser.getId());

        assertThat(userFound)
            .isEqualTo(null);
    }

    @Test
    public void checkGetAllUsersMethod() {
        userService.getAllUsers();
        Mockito.verify(userRepository).findAll();
    }

    @Test
    public void whenDeleteByValidUserId_thenReturnOne() {
        User testUser = new User(1L, "test_guy", null, null);

        Integer removingResult = userService.deleteUserById(testUser.getId());
        assertThat(removingResult).isEqualTo(1);
    }

    @Test
    public void whenDeleteByInValidUserId_thenReturnNull() {
        User testUser = new User(-5L, "test_guy", null, null);

        Integer removingResult = userService.deleteUserById(testUser.getId());
        assertThat(removingResult).isEqualTo(null);
    }

    @Test
    public void whenSaveValidUser_thenReturnNewUser() {
        User testUser = new User(1L, "test_guy", null, null);

        User foundUser = userService.saveUser(testUser);
        assertThat(foundUser).isEqualTo(testUser);
    }

    @Test
    public void whenSaveWithInValidUserName_thenReturnNull() {
        User testUser = new User(1L, "", null, null);

        User foundUser = userService.saveUser(testUser);
        assertThat(foundUser).isEqualTo(null);
    }


    
}
