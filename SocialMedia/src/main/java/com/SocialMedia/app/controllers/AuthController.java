package com.SocialMedia.app.controllers;

import com.SocialMedia.app.DTO.JwtRequest;
import com.SocialMedia.app.DTO.JwtResponse;
import com.SocialMedia.app.components.JwtTokenComponent;
import com.SocialMedia.app.exceptions.AuthError;
import com.SocialMedia.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenComponent jwtTokenComponent;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        String token = jwtTokenComponent.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
