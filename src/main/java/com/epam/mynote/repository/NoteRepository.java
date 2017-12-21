package com.epam.mynote.repository;

import com.epam.mynote.domain.Label;
import com.epam.mynote.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Note findNoteByIdAndNotebook_UserId(Long noteId, Long userId);

    List<Note> findAllByNotebook_IdAndNotebook_User_Id(Long notebookId, Long userId);

    List<Note> findAllByNotebook_User_Id(Long userId);

    Integer deleteNoteByIdAndNotebook_User_Id(Long noteId, Long userId);

    List<Note> getAllByLabelList(List<Label> labelList);
}
