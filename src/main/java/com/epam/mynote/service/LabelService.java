package com.epam.mynote.service;

import com.epam.mynote.domain.Label;
import com.epam.mynote.domain.Note;

import java.util.List;

public interface LabelService {

    Label getLabelByIdAndUserId(Long labelId, Long userId);

    List<Note> getNotesByLabels(Long labelId, Long userId);

    Label createLabelByUserId(Label label, Long userId);

    Integer deleteLabelByIdByUserId(Long labelId, Long userId);

    List<Label> getAllMarksByUserId(Long userId);

    String markNote(Long labelId, Long noteId, Long userId);

    String unMarkNote(Long labelId, Long noteId, Long userId);

}
