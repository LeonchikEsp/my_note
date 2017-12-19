package com.epam.mynote.service;

import com.epam.mynote.domain.Notebook;
import com.epam.mynote.domain.User;

import java.util.List;

public interface NotebookService {

    Notebook getNotebookByIdAndUser(Long id, User user);

    List<Notebook> getAllNotebooksByUser();

    boolean deleteNotebookByIdByUser(Long id);

    Notebook saveNotebookByUser(Notebook user);

}
