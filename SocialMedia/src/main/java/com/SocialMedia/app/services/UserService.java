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

    public Iterable<User> findAllUser() {
        return repository.findAll();
    }

    public Optional<User> findUserByLogin(String login) {
        return repository.findByLogin(login);
    }

    public User saveUser(User user) {
        repository.save(user);
        return user;
    }

    public User addUser(User user) throws UserNotFoundException {
        repository.save(user);
        return user;
    }

    public User deleteUser(User user) {
        Optional<User> resUser = repository.findByLogin(user.getLogin());
        if (resUser.isPresent()) {
            repository.delete(user);
            return resUser.get();
        }
        return null;
    }

    public User updateUser(User user) throws UserNotFoundException {
        deleteUser(user);
        addUser(user);
        return user;
    }

    public Note addNoteByUser(User user, Note note) throws UserNotFoundException {
        Optional<User> resUser = repository.findById(user.getLogin());
        if (!resUser.isPresent()) {
            throw new UserNotFoundException();
        }
        resUser.get().getNotes().add(note);
        return note;
    }

}
