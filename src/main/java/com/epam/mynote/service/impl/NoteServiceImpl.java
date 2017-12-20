package com.epam.mynote.service.impl;

import com.epam.mynote.domain.Note;
import com.epam.mynote.repository.NoteRepository;
import com.epam.mynote.repository.NotebookRepository;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Note getNoteByIdAndUserId(Long noteId, Long userId) {
        return null;
//        return noteRepository.findNoteByIdAndNotebook_UserId()
    }

    @Override
    public List<Note> getAllNotesByUserId(Long userId) {
        return null;
//        noteRepository.findNoteByIdAndNotebook_UserId()
    }

    @Override
    public List<Note> getAllNotesByUserIdByNotebookId(Long userId, Long notebookId) {
        return noteRepository.findAllByNotebook_IdAndNotebook_User_Id(notebookId, userId);
    }

    @Override
    public Integer deleteNoteByIdByUserId(Long id, Long userId) {
        return null;
    }

    @Override
    public Note saveNoteByNotebookIdByUserId(Note note, Long notebookId, Long userId) {
        return null;
    }
}
