package com.epam.mynote.repository;

import com.epam.mynote.domain.Label;
import com.epam.mynote.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> getAllByUserId(Long UserId);

    Label getByIdAndUserId(Long labelId, Long userId);
}
