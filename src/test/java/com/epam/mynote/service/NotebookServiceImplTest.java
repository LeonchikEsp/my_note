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
import java.util.ArrayList;
import java.util.Arrays;
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
        User baseUser2 = new User(
            5L,
            "test_guy_with_no_notebooks",
            null,
            null
        );
        User baseUser3 = new User(
            6L,
            "test_guy_with_notebooks",
            null,
            null
        );
        Notebook baseNotebook = new Notebook(1L, "test_notebook", baseUser, null);
        Notebook noSuchNotebook = new Notebook(2L, "test_notebook2", baseUser, null);
        Notebook baseNotebook2 = new Notebook(3L, "test_notebook3", baseUser3, null);
        Notebook baseNotebook3 = new Notebook(4L, "test_notebook4", baseUser3, null);
        Notebook baseNotebook4 = new Notebook(5L, "test_notebook4", baseUser3, null);


        Mockito.when(userRepository.findUserById(baseUser3.getId())).thenReturn(baseUser3);
        Mockito.when(userRepository.findUserById(baseUser2.getId())).thenReturn(baseUser2);
        Mockito.when(notebookRepository.findNotebookById(baseNotebook.getId()))
            .thenReturn(baseNotebook);
        Mockito.when(notebookRepository.findNotebookById(noSuchNotebook.getId()))
            .thenReturn(null);
        Mockito.when(notebookRepository.findAllByUserId(baseUser2.getId())).thenReturn(
            new ArrayList<>());
        Mockito.when(notebookRepository.findAllByUserId(baseUser3.getId()))
            .thenReturn(Arrays.asList(baseNotebook2, baseNotebook3));
        Mockito.when(notebookRepository.deleteNotebookByIdAndUser_Id(baseNotebook.getId(), baseUser.getId()))
            .thenReturn(1);

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

        notebookService.getNotebookByIdAndUserId(
            baseNotebook.getId(),
            baseUser.getId());
    }

    @Test(expected = NoNotebookFoundException.class)
    public void whenNoNotebookInDatabase_thenNoNotebookFoundException() {
        User baseUser = new User(1L, "test_guy", null, null);
        Notebook noSuchNotebook = new Notebook(2L, "test_notebook2", baseUser, null);

        notebookService.getNotebookByIdAndUserId(
            noSuchNotebook.getId(),
            baseUser.getId());
    }

    @Test(expected = AccessDeniedException.class)
    public void whenUserDoesntCorrespondToNotebook_thenAccessDeniedException() {
        User baseUser = new User(1L, "test_guy", null, null);
        Notebook baseNotebook = new Notebook(1L, "test_notebook", baseUser, null);

        notebookService.getNotebookByIdAndUserId(
            baseNotebook.getId(),
            baseUser.getId() + 1); //other user id
    }

    @Test(expected = InvalidDataException.class)
    public void whenInvalidUserId_thenNoNotebooksReturned() {
        User baseUser = new User(-2L, "test_guy", null, null);

        notebookService.getAllNotebooksByUserId(baseUser.getId());
    }

    @Test(expected = NoNotebookFoundException.class)
    public void whenUserHasNoNotebooks_thenNoNotebooksException() {
        User baseUser2 = new User(
            5L,
            "test_guy_with_no_notebooks",
            null,
            null
        );

        notebookService.getAllNotebooksByUserId(baseUser2.getId());
    }

    @Test
    public void whenUserIsValidAndHasNotebooks_thenReturnNotebooks() {
        User baseUser3 = new User(
            6L,
            "test_guy_with_notebooks",
            null,
            null
        );

        Assertions.assertThat(notebookService.getAllNotebooksByUserId(baseUser3.getId()).size())
            .isEqualTo(2);
    }

    @Test(expected = InvalidDataException.class)
    public void whenUserIdOrNotebookIdInvalid_thenInvalidData() {
        User baseUser3 = new User(
            -6L,
            "test_guy",
            null,
            null
        );
        Notebook baseNotebook2 = new Notebook(-3L, "test_notebook3", baseUser3, null);

        notebookService.deleteNotebookByIdByUserId(baseNotebook2.getId(), baseUser3.getId());
    }

    @Test(expected = NoNotebookFoundException.class)
    public void whenNoNotebook_cantDelete() {
        User baseUser = new User(
            2L,
            "test_guy_with_notebooks",
            null,
            null
        );
        Notebook baseNotebook2 = new Notebook(2L, "test_notebook3", baseUser, null);

        notebookService.deleteNotebookByIdByUserId(baseNotebook2.getId(), baseUser.getId());
    }

    @Test
    public void whenUserIdAndNotebookIdIsValid_thenReturnOne() {
        User baseUser1 = new User(1L, "test_guy", null, null);

        Notebook baseNotebook = new Notebook(1L, "test_notebook", baseUser1, null);

        Assertions.assertThat(
            notebookService.deleteNotebookByIdByUserId(baseNotebook.getId(), baseUser1.getId())
        ).isEqualTo(1);
    }



}
