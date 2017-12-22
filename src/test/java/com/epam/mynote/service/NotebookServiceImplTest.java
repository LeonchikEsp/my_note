package com.epam.mynote.service;

import com.epam.mynote.domain.Notebook;
import com.epam.mynote.domain.User;
import com.epam.mynote.exceptions.AccessDeniedException;
import com.epam.mynote.exceptions.InvalidDataException;
import com.epam.mynote.exceptions.NoNotebookFoundException;
import com.epam.mynote.repository.NotebookRepository;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.impl.NotebookServiceImpl;
import com.epam.mynote.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
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
public class NotebookServiceImplTest {
    @TestConfiguration
    static class NotebookServiceImplTestContextConfiguration {

        @Bean
        public NotebookService notebookService() {
            return new NotebookServiceImpl();
        }

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private NotebookRepository notebookRepository;

    @Before
    public void setUp() {
        User baseUser = new User(1L, "test_guy", null, null);
        Notebook baseNotebook = new Notebook(1L, "test_notebook", baseUser, null);
        Notebook noSuchNotebook = new Notebook(2L, "test_notebook2", baseUser, null);


        Mockito.when(notebookRepository.findNotebookById(baseNotebook.getId()))
            .thenReturn(baseNotebook);
        Mockito.when(notebookRepository.findNotebookById(noSuchNotebook.getId()))
            .thenReturn(null);

    }

    @Test
    public void whenValidUserIdAndNotebookId_thenReturnNotebook() {
        User baseUser = new User(1L, "test_guy", null, null);
        Notebook baseNotebook = new Notebook(1L, "test_notebook", baseUser, null);

        Notebook notebookFound = notebookService.getNotebookByIdAndUserId(
            baseNotebook.getId(),
            baseUser.getId());

        Assertions.assertThat(notebookFound).isEqualTo(baseNotebook);
    }


    @Test(expected = InvalidDataException.class)
    public void whenInvalidUserIdAndNotebookId_thenInvalidDataException() {
        User baseUser = new User(-5L, "test_guy", null, null);
        Notebook baseNotebook = new Notebook(1L, "test_notebook", baseUser, null);

        Notebook notebookFound = notebookService.getNotebookByIdAndUserId(
            baseNotebook.getId(),
            baseUser.getId());
    }

    @Test(expected = NoNotebookFoundException.class)
    public void whenNoNotebookInDatabase_thenNoNotebookFoundException() {
        User baseUser = new User(1L, "test_guy", null, null);
        Notebook noSuchNotebook = new Notebook(2L, "test_notebook2", baseUser, null);

        Notebook notebookFound = notebookService.getNotebookByIdAndUserId(
            noSuchNotebook.getId(),
            baseUser.getId());
    }

    @Test(expected = AccessDeniedException.class)
    public void whenUserDoesntCorrespondToNotebook_thenAccessDeniedException() {
        User baseUser = new User(1L, "test_guy", null, null);
        Notebook baseNotebook = new Notebook(1L, "test_notebook", baseUser, null);

        Notebook notebookFound = notebookService.getNotebookByIdAndUserId(
            baseNotebook.getId(),
            baseUser.getId() + 1); //other user id
    }




}
