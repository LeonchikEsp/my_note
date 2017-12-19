package com.epam.mynote.service.impl;

import com.epam.mynote.domain.Notebook;
import com.epam.mynote.domain.User;
import com.epam.mynote.repository.NotebookRepository;
import com.epam.mynote.service.NotebookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotebookServiceImpl implements NotebookService {

    private final NotebookRepository notebookRepository;

    public NotebookServiceImpl(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }

    @Override
    public Notebook getNotebookByIdAndUser(Long id, User userl) {
        return null;
    }

    @Override
    public List<Notebook> getAllNotebooksByUser() {
        return null;
    }

    @Override
    public boolean deleteNotebookByIdByUser(Long id) {
        return false;
    }

    @Override
    public Notebook saveNotebookByUser(Notebook user) {
        return null;
    }
}
