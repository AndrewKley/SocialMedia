package com.SocialMedia.app.controllers;

import com.SocialMedia.app.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SocialMedia.app.models.User;

import java.util.Optional;

// curl -v -X GET http://localhost:8080/users
// curl -v -X POST http://localhost:8080/users -d '{"login": "arkadiy", "password": "123456", "role": "ADMIN"}' -H "Content-Type: application/json"

@RestController
@RequestMapping(value = "/users", produces = "application/json")
@CrossOrigin(origins = "http://socialmedia:8080")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping
    public Iterable<User> findAllUsers() {
        return service.findAllUser();
    }

    @GetMapping("{login}")
    public ResponseEntity<User> findUserById(@PathVariable("login") String login) {
        Optional<User> user = service.findUserByLogin(login);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user) {
        service.saveUser(user);
        return user;
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> putUser(@RequestBody User user) {
        Optional<User> tmpUser = service.findUserByLogin(user.getLogin());
        if (!tmpUser.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (user.getPassword() != null) {
            service.deleteUser(user);
            service.saveUser(tmpUser.get());
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        Optional<User> tmpUser = service.findUserByLogin(user.getLogin());
        if (tmpUser.isPresent()) {
            service.deleteUser(user);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
