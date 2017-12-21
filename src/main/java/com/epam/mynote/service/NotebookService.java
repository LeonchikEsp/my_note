package com.epam.mynote.service;

import com.epam.mynote.domain.Notebook;

import java.util.List;

public interface NotebookService {

    Notebook getNotebookByIdAndUserId(Long id, Long userId);

    List<Notebook> getAllNotebooksByUserId(Long userId);

    Integer deleteNotebookByIdByUserId(Long id, Long userId);

    Notebook saveNotebookByUserId(Notebook notebook, Long userId);

}
