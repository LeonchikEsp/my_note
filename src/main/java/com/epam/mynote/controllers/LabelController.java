package com.epam.mynote.controllers;

import com.epam.mynote.domain.Label;
import com.epam.mynote.service.LabelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LabelController {

    private final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping(value = "/user/{userId}/label/{labelId}")
    public Label getLabelByIdAndUserId(@PathVariable("userId") Long userId,
                                       @PathVariable("labelId") Long labelId) {
        return labelService.getLabelByIdAndUserId(labelId, userId);
    }

    @PostMapping(value = "/user/{userId}/label/{labelId}")
    public Label createLabelByUserId(@PathVariable("userId") Long userId,
                                     @RequestBody Label label) {
        return labelService.createLabelByUserId(label, userId);
    }

    @DeleteMapping(value = "/user/{userId}/label/{labelId}")
    public Integer deleteLabelByUserId(@PathVariable("userId") Long userId,
                                       @PathVariable("labelId") Long labelId) {
        return labelService.deleteLabelByIdByUserId(labelId, userId);
    }

    @GetMapping(value = "/user/{userId}/label")
    public List<Label> getAllMarksByUserId(@PathVariable("userId") Long userId) {
        return labelService.getAllMarksByUserId(userId);
    }

    @GetMapping(value = "/user/{userId}/note/{noteId}/label/{labelId}/mark")
    public String markNote(@PathVariable("userId") Long userId,
                           @PathVariable("noteId") Long noteId,
                           @PathVariable("labelId") Long labelId) {
        return labelService.markNote(labelId, noteId, userId);
    }

    @GetMapping(value = "/user/{userId}/note/{noteId}/label/{labelId}/unmark")
    public String unMarkNote(@PathVariable("userId") Long userId,
                             @PathVariable("noteId") Long noteId,
                             @PathVariable("labelId") Long labelId) {
        return labelService.unMarkNote(labelId, noteId, userId);
    }
}
