package com.epam.mynote.service.impl;

import com.epam.mynote.aspect.LogForExecutionTime;
import com.epam.mynote.domain.Label;
import com.epam.mynote.domain.Note;
import com.epam.mynote.domain.User;
import com.epam.mynote.repository.LabelRepository;
import com.epam.mynote.repository.NoteRepository;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.LabelService;
import com.epam.mynote.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    private final NoteRepository noteRepository;

    private final UserRepository userRepository;

    public LabelServiceImpl(LabelRepository labelRepository, NoteRepository noteRepository, UserRepository userRepository) {
        this.labelRepository = labelRepository;
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @LogForExecutionTime
    @Override
    public Label getLabelByIdAndUserId(Long labelId, Long userId) {
        if (labelId != null && userId != null)
            return labelRepository.getByIdAndUserId(labelId, userId);
        return null;
    }

    @LogForExecutionTime
    @Override
    public List<Note> getNotesByLabels(Long labelId, Long userId) {
        if (labelId != null && userId != null) {
            Label label = labelRepository.getByIdAndUserId(labelId, userId);
            return noteRepository.getAllByLabelList(Collections.singletonList(label));
        }
        return null;
    }

    @LogForExecutionTime
    @Override
    public Label createLabelByUserId(Label label, Long userId) {
        if (label != null && userId != null) {
            User user = userRepository.findUserById(userId);
            if (user != null) {
                Label newLabel = new Label();
                newLabel.setName(label.getName());
                newLabel.setUser(user);
                return labelRepository.save(label);
            }
            return null;
        }
        return null;
    }

    @LogForExecutionTime
    @Override
    public Integer deleteLabelByIdByUserId(Long labelId, Long userId) {
        User user = userRepository.findUserById(userId);
        Label label = labelRepository.getByIdAndUserId(labelId, userId);
        if (user.getLabelList().contains(label)) {
            labelRepository.delete(labelId);
        }
        return null;
    }

    @LogForExecutionTime
    @Override
    public List<Label> getAllMarksByUserId(Long userId) {
        return labelRepository.getAllByUserId(userId);
    }

    @LogForExecutionTime
    @Override
    public String markNote(Long labelId, Long noteId, Long userId) {
        Label label = labelRepository.getByIdAndUserId(labelId, userId);
        Note note = noteRepository.findNoteByIdAndNotebook_UserId(noteId, userId);
        if (label != null && note != null){
            note.getLabelList().add(label);
            noteRepository.save(note);
        }
        return null;
    }

    @LogForExecutionTime
    @Override
    public String unMarkNote(Long labelId, Long noteId, Long userId) {
        Label label = labelRepository.getByIdAndUserId(labelId, userId);
        Note note = noteRepository.findNoteByIdAndNotebook_UserId(noteId, userId);
        if (label != null && note != null){
            note.getLabelList().remove(label);
            noteRepository.save(note);
        }
        return null;
    }
}
