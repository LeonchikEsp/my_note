package com.epam.mynote.controllers;

import com.epam.mynote.domain.Notebook;
import com.epam.mynote.service.NotebookService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotebookController {

    @Autowired
    private NotebookService notebookService;

    @GetMapping(value = "/user/{userId}/notebook")
    public ResponseEntity<List<Notebook>> getNotebooksByUserId(@PathVariable("userId") Long userId) {

        List<Notebook> notebooksList = notebookService.getAllNotebooksByUserId(userId);
        return new ResponseEntity<>(notebooksList, HttpStatus.OK);
    }

    @PostMapping(value = "/user/{userId}/notebook")
    public Notebook saveNotebookByUserId(@PathVariable("userId") Long userId,
                                         @RequestBody Notebook notebook) {
        return notebookService.saveNotebookByUserId(notebook, userId);
    }

    @GetMapping(value = "/user/{userId}/notebook/{notebookId}")
    public Notebook getNotebookByIdAndUserId(@PathVariable("userId") Long userId,
                                             @PathVariable("notebookId") Long notebookId) {
        return notebookService.getNotebookByIdAndUserId(notebookId, userId);
    }

    @DeleteMapping(value = "/user/{userId}/notebook/{notebookId}")
    public Integer deleteNotebookByIdByUserId(@PathVariable("userId") Long userId,
                                              @PathVariable("notebookId") Long notebookId) {
        return notebookService.deleteNotebookByIdByUserId(notebookId, userId);
    }
}
