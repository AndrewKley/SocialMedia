package com.SocialMedia.app.controllers;

import com.SocialMedia.app.DTO.JwtRequest;
import com.SocialMedia.app.DTO.JwtResponse;
import com.SocialMedia.app.DTO.RegistrationUserDTO;
import com.SocialMedia.app.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        return ResponseEntity.ok(authService.createAuthToken(request));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createUser(@RequestBody RegistrationUserDTO userDTO) throws Exception {
        return authService.createUser(userDTO);
    }
}
