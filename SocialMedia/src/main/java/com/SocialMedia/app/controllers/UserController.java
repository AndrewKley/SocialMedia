package com.SocialMedia.app.controllers;

import com.SocialMedia.app.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SocialMedia.app.models.User;

import java.util.Optional;


@RestController
@RequestMapping(value = "/users", produces = "application/json")
@CrossOrigin(origins = "http://socialmedia:8080")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("{login}")
    public ResponseEntity<User> findUserById(@PathVariable("login") String login) {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> putUser(@RequestBody User user) {
        Optional<User> tmpUser = userRepository.findByLogin(user.getLogin());
        if (!tmpUser.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (user.getPassword() != null) {
            userRepository.delete(user);
            userRepository.save(tmpUser.get());
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        Optional<User> tmpUser = userRepository.findByLogin(user.getLogin());
        if (tmpUser.isPresent()) {
            userRepository.delete(user);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
