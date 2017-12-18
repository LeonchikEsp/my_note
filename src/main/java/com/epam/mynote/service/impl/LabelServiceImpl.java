package com.epam.mynote.service.impl;

import com.epam.mynote.domain.Label;
import com.epam.mynote.domain.User;
import com.epam.mynote.repository.LabelRepository;
import com.epam.mynote.service.LabelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label save(Label label) {
        return labelRepository.save(label);
    }

    public Label save(Long userId, Label label) {
        return null;
    }

    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    @Override
    public Label getLabelById() {
        return null;
    }

    public List<Label> getLabelById(Long id) {
        return null;
    }


}
