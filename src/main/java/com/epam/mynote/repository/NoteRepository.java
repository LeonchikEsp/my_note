package com.epam.mynote.repository;

import com.epam.mynote.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Note findNoteByIdAndNotebook_UserId(Long notebookId, Long userId);

    List<Note> findAllByNotebook_IdAndNotebook_User_Id(Long notebookId, Long userId);

    Integer deleteNoteById(Long noteId);
}
