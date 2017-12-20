package com.epam.mynote.controllers;

import com.epam.mynote.domain.Notebook;
import com.epam.mynote.service.NotebookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotebookController {

    private final NotebookService notebookService;

    public NotebookController(NotebookService notebookService) {
        this.notebookService = notebookService;
    }

    @GetMapping(value = "/user/{userId}/notebook")
    public List<Notebook> getNotebooksByUserId(@PathVariable("userId") Long userId) {
        return notebookService.getAllNotebooksByUserId(userId);
    }

    @PostMapping(value = "/user/{userId}/notebook")
    public Notebook saveNotebook(@PathVariable("userId") Long userId, @RequestBody Notebook notebook) {
        return notebookService.saveNotebookByUserId(notebook, userId);
    }

    @GetMapping(value = "/user/{userId}/notebook/{notebookId}")
    public Notebook getAllUsers(@PathVariable("userId") Long userId, @PathVariable("notebookId") Long notebookId) {
        return notebookService.getNotebookByIdAndUserId(notebookId, userId);
    }

    @DeleteMapping(value = "/user/{userId}/notebook/{notebookId}")
    public Integer deleteUserById(@PathVariable("userId") Long userId, @PathVariable("notebookId") Long notebookId) {
        return notebookService.deleteNotebookByIdByUserId(notebookId, userId);
    }
}
