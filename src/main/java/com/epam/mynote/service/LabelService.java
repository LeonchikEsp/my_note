package com.epam.mynote.service;

import com.epam.mynote.domain.Label;

import java.util.List;

public interface LabelService {

    Label save(Label label);

    Label save(Long userId, Label label);

    List<Label> getAllLabels();

    Label getLabelById();
}
