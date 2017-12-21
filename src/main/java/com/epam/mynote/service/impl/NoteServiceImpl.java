package com.epam.mynote.service.impl;

import com.epam.mynote.aspect.LogForExecutionTime;
import com.epam.mynote.domain.Note;
import com.epam.mynote.domain.Notebook;
import com.epam.mynote.repository.NoteRepository;
import com.epam.mynote.repository.NotebookRepository;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class NoteServiceImpl implements NoteService {

    private final NotebookRepository notebookRepository;

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    public NoteServiceImpl(NotebookRepository notebookRepository, NoteRepository noteRepository, UserRepository userRepository) {
        this.notebookRepository = notebookRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @LogForExecutionTime
    @Override
    public Note getNoteByIdAndUserId(Long noteId, Long userId) {
        return noteRepository.findNoteByIdAndNotebook_UserId(noteId, userId);
    }

    @LogForExecutionTime
    @Override
    public List<Note> getAllNotesByUserId(Long userId) {
        return noteRepository.findAllByNotebook_User_Id(userId);
    }

    @LogForExecutionTime
    @Override
    public List<Note> getAllNotesByUserIdByNotebookId(Long userId, Long notebookId) {
        return noteRepository.findAllByNotebook_IdAndNotebook_User_Id(notebookId, userId);
    }

    @LogForExecutionTime
    @Override
    public Integer deleteNoteByIdByUserId(Long id, Long userId) {
        return noteRepository.deleteNoteByIdAndNotebook_User_Id(id, userId);
    }

    @LogForExecutionTime
    @Override
    public Note saveNoteByNotebookIdByUserId(Note note, Long notebookId, Long userId) {
        Notebook notebook = notebookRepository.findNotebookByIdAndUserId(notebookId, userId);
        if (notebook != null) {
            Note newNote = new Note();
            newNote.setContent(note.getContent());
            newNote.setName(note.getName());
            newNote.setNotebook(notebook);

            return noteRepository.save(newNote);
        }
        return null;
    }
}
