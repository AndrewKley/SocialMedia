package com.SocialMedia.app.services;

import com.SocialMedia.app.exceptions.NoteNotFoundException;
import com.SocialMedia.app.exceptions.UserNotFoundException;
import com.SocialMedia.app.models.Note;
import com.SocialMedia.app.models.User;
import com.SocialMedia.app.repositories.NoteRepository;
import com.SocialMedia.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;
    private NoteService noteService;

    @Autowired
    public UserService(UserRepository repository, NoteService noteService) {
        this.repository = repository;
        this.noteService = noteService;
    }


    public User addUser(User user) throws UserNotFoundException {
        repository.save(user);
        return user;
    }

    public User deleteUser(User user) throws UserNotFoundException {
        Optional<User> resUser = repository.findByLogin(user.getLogin());
        if (!resUser.isPresent()) {
            throw new UserNotFoundException();
        }
        repository.delete(user);
        return resUser.get();
    }

    public User updateUser(User user) throws UserNotFoundException {
        deleteUser(user);
        addUser(user);
        return user;
    }

    public Note addNoteByUser(User user, Note note) {
        Optional<User> resUser = repository.findById(user.getLogin());
        Optional<Note> resNote = noteService.findNote(note);

//        if (!resUser.isPresent()) {
//            throw new UserNotFoundException();
//        }
//        if (!resNote.isPresent()) {
//            throw new NoteNotFoundException();
//        }
//        resUser.get().getNotes().get(note.getId());
        return resNote.get();
    }

}
