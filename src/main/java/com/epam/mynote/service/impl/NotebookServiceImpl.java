package com.epam.mynote.service.impl;

import com.epam.mynote.repository.NotebookRepository;
import com.epam.mynote.service.NotebookService;
import org.springframework.stereotype.Service;

@Service
public class NotebookServiceImpl implements NotebookService {

    private final NotebookRepository notebookRepository;

    public NotebookServiceImpl(NotebookRepository notebookRepository) {
        this.notebookRepository = notebookRepository;
    }
}
