package com.epam.mynote.service;

import com.epam.mynote.domain.Note;

import java.util.List;

public interface NoteService {

    Note getNoteByIdAndUserId(Long noteId, Long userId);

    List<Note> getAllNotesByUserId(Long userId);

    List<Note> getAllNotesByUserIdByNotebookId(Long userId, Long notebookId);

    Integer deleteNoteByIdByUserId(Long id, Long userId);

    Note saveNoteByNotebookIdByUserId(Note note, Long notebookId, Long userId);
}
