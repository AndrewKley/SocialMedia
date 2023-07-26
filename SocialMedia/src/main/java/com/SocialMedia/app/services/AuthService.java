package com.SocialMedia.app.services;

import com.SocialMedia.app.DTO.JwtRequest;
import com.SocialMedia.app.DTO.JwtResponse;
import com.SocialMedia.app.DTO.RegistrationUserDTO;
import com.SocialMedia.app.components.JwtTokenComponent;
import com.SocialMedia.app.exceptions.AuthError;
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

    public ResponseEntity<?> createAuthToken(JwtRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AuthError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        String token = jwtTokenComponent.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createUser(RegistrationUserDTO userDTO) throws Exception {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            return new ResponseEntity<>(new AuthError(HttpStatus.BAD_REQUEST.value(), "Password mistmatch"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findUserByLogin(userDTO.getLogin()).isPresent()) {
            return new ResponseEntity<>(new AuthError(HttpStatus.BAD_REQUEST.value(),"User with this login exists"), HttpStatus.BAD_REQUEST);
        }

        userService.createUser(userDTO);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}