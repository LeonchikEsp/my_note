package com.epam.mynote.service;

import com.epam.mynote.domain.Note;
import com.epam.mynote.domain.Notebook;
import com.epam.mynote.domain.User;
import com.epam.mynote.repository.NoteRepository;
import com.epam.mynote.repository.NotebookRepository;
import com.epam.mynote.service.impl.NoteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceImplTest {

    private User user;
    private User user1;
    private Notebook notebook;
    private Notebook notebook1;
    private List<Notebook> notebookList = new ArrayList<>();
    List<Note> noteList = new ArrayList<>();
    private Note note;
    private Note note1;

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private NotebookRepository notebookRepository;

    @InjectMocks
    private NoteServiceImpl noteService;

    @Before
    public void setUp() {
        user = new User(1L, "test_guy", null, null);
        user1 = new User(2L, "test_guy1", null, null);

        notebook = new Notebook(1L, "notebookName", user, null);
        notebook1 = new Notebook(2L, "notebookName1", user, null);
        notebookList.add(notebook);
        user.setNotebookList(notebookList);

        note = new Note(1L, "noteName", "noteContent", notebook, null);
        note1 = new Note(1L, "testNoteName", "testNoteContent", notebook, null);
        noteList.add(note);
        noteList.add(note1);
    }

    //TODO Check test
//    @Test
//    public void saveNoteByNotebookIdByUserIdUserExistNoteExist() {
//        when(notebookRepository.findNotebookByIdAndUserId(anyLong(), anyLong()))
//                .thenReturn(notebook);
//        when(noteRepository.save(note))
//                .thenReturn(note);
//        Note noteRes = noteService.saveNoteByNotebookIdByUserId(this.note, notebook.getId(), user.getId());
//
//        assertThat(noteRes).isNotNull();
//        assertThat(noteRes.getNotebook()).isEqualTo(notebook);
//    }

    @Test
    public void saveNoteByNotebookIdByUserIdNotebookNull() {
        when(notebookRepository.findNotebookByIdAndUserId(anyLong(), anyLong()))
                .thenReturn(null);
        when(noteRepository.save(note))
                .thenReturn(note);
        Note noteRes = noteService.saveNoteByNotebookIdByUserId(this.note, notebook.getId(), user.getId());

        assertThat(noteRes).isNull();
    }
}
