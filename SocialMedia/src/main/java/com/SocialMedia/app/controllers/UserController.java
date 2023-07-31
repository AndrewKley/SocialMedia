package com.SocialMedia.app.controllers;

import com.SocialMedia.app.DTO.RegistrationUserDTO;
import com.SocialMedia.app.DTO.RequestUserDTO;
import com.SocialMedia.app.DTO.ResponseUserDTO;
import com.SocialMedia.app.exceptions.RegistrationUserException;
import com.SocialMedia.app.exceptions.UserNotFoundException;
import com.SocialMedia.app.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SocialMedia.app.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

// curl -v -X GET http://localhost:8080/users
// curl -v -X POST http://localhost:8080/users -d '{"login": "arkadiy", "password": "123456", "role": "ADMIN"}' -H "Content-Type: application/json"

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
@CrossOrigin(origins = "http://socialmedia:8080")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping(consumes = "application/json")
    public ResponseEntity<List<ResponseUserDTO>> findAllUsers() {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(service.convertUserToResponseUserDTO(service.findAllUser()));
    }

    @GetMapping(value = "{login}", consumes = "application/json")
    public ResponseEntity<?> findUserByLogin(@PathVariable("login") String login) {
        Optional<User> user = service.findUserByLogin(login);
        if (user.isPresent()) {
            return new ResponseEntity<>(service.convertUserToResponseUserDTO(user.get()), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(new UserNotFoundException("user not found"), HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody RegistrationUserDTO user) throws RegistrationUserException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.convertUserToResponseUserDTO(service.saveUser(user)));
        } catch (RegistrationUserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e);
        }
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(service.updateUser(user), HttpStatus.ACCEPTED);
        } catch (RegistrationUserException e) {
            return new ResponseEntity<>(new RegistrationUserException(), HttpStatus.NOT_FOUND);
        }
    }
//
//    @PatchMapping(consumes = "application/json")
//    public ResponseEntity<?> patchUser(@RequestBody User user) {
//
//    }

    @DeleteMapping(consumes = "application/json")
    public ResponseEntity<User> deleteUser(@RequestBody RequestUserDTO user) {
        return new ResponseEntity<>(service.deleteUser(user), HttpStatus.ACCEPTED);
    }
}
