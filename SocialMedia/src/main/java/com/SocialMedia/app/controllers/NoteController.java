package com.SocialMedia.app.controllers;

import com.SocialMedia.app.models.Note;
import com.SocialMedia.app.services.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// curl -v -X GET http://localhost:8080/users/note
// curl -v -X POST http://localhost:8080/users/note -d '{"header": "Cart", "body": "Buy a chocolate, a car", "user": {"login": "arkadiy", "password": "123456", "role": "ADMIN"}}' -H "Content-Type: application/json"

@RestController
@RequestMapping(value = "/users/note", produces = "application/json")
public class NoteController {
    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Note> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Note addNote(@RequestBody Note note) {
        // check user exists
        // user adds note too
        service.addNote(note);
        return note;
    }
}
