package com.epam.mynote.service.impl;

import com.epam.mynote.aspect.LogForExecutionTime;
import com.epam.mynote.domain.Notebook;
import com.epam.mynote.domain.User;
import com.epam.mynote.exceptions.AccessDeniedException;
import com.epam.mynote.exceptions.InvalidDataException;
import com.epam.mynote.exceptions.NoNoteFoundException;
import com.epam.mynote.exceptions.NoNotebookFoundException;
import com.epam.mynote.exceptions.NoUserFoundException;
import com.epam.mynote.repository.NotebookRepository;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.NotebookService;
import com.epam.mynote.service.UserService;
import com.epam.mynote.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class NotebookServiceImpl implements NotebookService {

    @Autowired
    private NotebookRepository notebookRepository;

    @Autowired
    private UserService userService;

    @Override
    public Notebook getNotebookByIdAndUserId(Long notebookId, Long userId) {
        if (!Validator.validId(notebookId) || !Validator.validId(userId))
            throw new InvalidDataException("invalid data to proceed");
        Notebook notebook = notebookRepository.findNotebookById(notebookId);
        if (notebook == null)
            throw new NoNotebookFoundException("no notebook found");
        if (!notebook.getUser().getId().equals(userId))
            throw new AccessDeniedException("no rights to see this notebook");

        return notebook;
    }

    @LogForExecutionTime
    @Override
    public List<Notebook> getAllNotebooksByUserId(Long userId) {
        if (!Validator.validId(userId))
            throw new InvalidDataException("invalid userId");
        if (userService.getUserById(userId) == null)
            throw new NoUserFoundException("no user found");

        List<Notebook> notebookList = notebookRepository.findAllByUserId(userId);
        if (notebookList.size() == 0)
            throw new NoNotebookFoundException("user doesnt has notebooks");
        return notebookList;
    }

    @LogForExecutionTime
    @Override
    public Integer deleteNotebookByIdByUserId(Long notebookId, Long userId) {
        if (!Validator.validId(notebookId) || !Validator.validId(userId))
            throw new InvalidDataException("invalid data to proceed");
        Notebook notebook = getNotebookByIdAndUserId(notebookId, userId);
        if (notebook == null)
            throw new NoNotebookFoundException("no notebook to delete");
        return notebookRepository.deleteNotebookByIdAndUser_Id(notebookId, userId);
    }

    @LogForExecutionTime
    @Override
    public Notebook saveNotebookByUserId(Notebook notebook, Long userId) {
        if (Validator.validId(userId))
            throw new InvalidDataException("invalid userId to add or update notebook");
        User user = userService.getUserById(userId);
        if (user == null)
            throw new NoUserFoundException("no user found");
        notebook.setUser(user);
        return notebook;
    }
}
