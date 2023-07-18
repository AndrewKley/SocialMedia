package com.SocialMedia.app.services;

import com.SocialMedia.app.exceptions.NoteNotFoundException;
import com.SocialMedia.app.models.Note;
import com.SocialMedia.app.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository repository;

    public Iterable<Note> findAll() {
        return repository.findAll();
    }

    public Optional<Note> findNote(Note note) {
        return repository.findById(note.getId());
    }

    public Note addNote(Note note) {
        repository.save(note);
        return note;
    }

    public Note deleteNote(Note note) throws NoteNotFoundException {
        Optional<Note> resNote = repository.findById(note.getId());
        if (!resNote.isPresent()) {
            throw new NoteNotFoundException();
        }
        repository.deleteById(resNote.get().getId());
        return resNote.get();
    }

    public Note updateNote(Note note) throws NoteNotFoundException {
        deleteNote(note);
        addNote(note);
        return note;
    }
}
