package com.epam.mynote.repository;

import com.epam.mynote.domain.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {

    Notebook findNotebookByIdAndUserId(Long id, Long userId);

    List<Notebook> findAllByUserId(Long userId);

    Integer deleteNotebookByIdAndUser_Id(Long id, Long userId);

}
