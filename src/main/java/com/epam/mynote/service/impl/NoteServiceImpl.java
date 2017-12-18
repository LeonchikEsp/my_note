package com.epam.mynote.service.impl;

import com.epam.mynote.domain.Label;
import com.epam.mynote.domain.User;
import com.epam.mynote.repository.NoteRepository;
import com.epam.mynote.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Label save(Label label) {
        return null;
    }

    public Label save(Long userId, Label label) {
        return null;
    }

    public Label update(Long labelId) {
        return null;
    }

    public List<Label> getLabelsByUser(User user) {
        return null;
    }
}
