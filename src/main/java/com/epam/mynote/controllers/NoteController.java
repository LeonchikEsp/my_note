package com.epam.mynote.controllers;

import com.epam.mynote.domain.Note;
import com.epam.mynote.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(value = "/user/{userId}/note")
    public List<Note> getNotesByUserId(@PathVariable("userId") Long userId) {
        return noteService.getAllNotesByUserId(userId);
    }

    @PostMapping(value = "/user/{userId}/notebook/{notebookId}/note")
    public Note saveNote(@PathVariable("userId") Long userId,
                         @PathVariable("notebookId") Long notebookId,
                         @RequestBody Note note) {
        return noteService.saveNoteByNotebookIdByUserId(note, notebookId, userId);
    }

    @GetMapping(value = "/user/{userId}/note/{noteId}")
    public Note getNoteByIdByUserIdB(@PathVariable("userId") Long userId,
                                     @PathVariable("noteId") Long noteId) {
        return noteService.getNoteByIdAndUserId(noteId, userId);
    }

    @DeleteMapping(value = "/user/{userId}/note/{noteId}")
    public Integer deleteUserById(@PathVariable("userId") Long userId,
                                  @PathVariable("noteId") Long noteId) {
        return noteService.deleteNoteByIdByUserId(noteId, userId);
    }

}
