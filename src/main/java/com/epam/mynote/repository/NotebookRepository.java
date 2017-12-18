package com.epam.mynote.repository;

import com.epam.mynote.domain.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotebookRepository extends JpaRepository<Notebook, Long> {
}
