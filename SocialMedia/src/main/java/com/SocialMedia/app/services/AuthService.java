package com.SocialMedia.app.services;

import com.SocialMedia.app.DTO.RequestUserDTO;
import com.SocialMedia.app.DTO.JwtResponse;
import com.SocialMedia.app.DTO.RegistrationUserDTO;
import com.SocialMedia.app.DTO.ResponseUserDTO;
import com.SocialMedia.app.components.JwtTokenComponent;
import com.SocialMedia.app.exceptions.AuthError;
import com.SocialMedia.app.exceptions.RegistrationUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenComponent jwtTokenComponent;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(RequestUserDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        String token = jwtTokenComponent.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createUser(RegistrationUserDTO userDTO) {
        try {
            userService.saveUser(userDTO);
            return new ResponseEntity<>(new ResponseUserDTO(userDTO.getLogin(), userDTO.getRoles()), HttpStatus.CREATED);
        } catch (RegistrationUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}