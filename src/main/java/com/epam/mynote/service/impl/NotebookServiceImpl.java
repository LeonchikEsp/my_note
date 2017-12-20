package com.epam.mynote.service.impl;

import com.epam.mynote.domain.Notebook;
import com.epam.mynote.domain.User;
import com.epam.mynote.repository.NotebookRepository;
import com.epam.mynote.repository.UserRepository;
import com.epam.mynote.service.NotebookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotebookServiceImpl implements NotebookService {

    private final NotebookRepository notebookRepository;

    private final UserRepository userRepository;

    public NotebookServiceImpl(NotebookRepository notebookRepository, UserRepository userRepository) {
        this.notebookRepository = notebookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Notebook getNotebookByIdAndUserId(Long id, Long userId) {
        return notebookRepository.findNotebookByIdAndUserId(id, userId);
    }

    @Override
    public List<Notebook> getAllNotebooksByUserId(Long userId) {
        return notebookRepository.findAllByUserId(userId);
    }

    @Transactional
    @Override
    public Integer deleteNotebookByIdByUserId(Long id, Long userId) {
        return notebookRepository.deleteNotebookByIdAndUser_Id(id, userId);
    }

    @Override
    public Notebook saveNotebookByUserId(Notebook notebook, Long userId) {
        Notebook newNotebook = new Notebook();
        User user = userRepository.findUserById(userId);
        if (user != null) {
            newNotebook.setUser(user);
            newNotebook.setName(notebook.getName());
            return notebookRepository.save(newNotebook);
        }
        return null;
    }
}
