package com.epam.mynote.repository;

import com.epam.mynote.domain.Notebook;
import com.epam.mynote.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {

    Notebook findNotebookByIdAndUser(Long id, User user);

    List<Notebook> findAllByUser(User user);

    boolean deleteNotebookByIdAndUser(Long id, User user);

}
