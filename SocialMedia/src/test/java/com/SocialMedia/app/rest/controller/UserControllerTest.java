package com.SocialMedia.app.rest.controller;

import com.SocialMedia.app.DTO.JwtRequest;
import com.SocialMedia.app.DTO.ResponseUserDTO;
import com.SocialMedia.app.controllers.UserController;
import com.SocialMedia.app.models.Role;
import com.SocialMedia.app.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.util.List;

import java.net.http.HttpRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
//    @Mock
//    UserService userService;
//
//    @InjectMocks
//    UserController userController;
//
    @Autowired
    TestRestTemplate testRestTemplate;
//
//    @Test
//    void findAllUsers_ReturnValidResponseEntity() {
//        var users = List.of(new JwtRequest("ark", "123456"));
//
//        Mockito.doReturn(users).when(this.userService).findAllUser();
//
//        var response = this.userController.findAllUsers();
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
//        assertEquals(users, response.getBody());
//   }

    @Test
    void findUserByLogin_LoginFound_ReturnValidResponseEntity() {
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var response = testRestTemplate.exchange("/users/{ark}", HttpMethod.GET, new HttpEntity<>(headers), ResponseUserDTO.class, "ark");

        assertNotNull(response);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());

        if (response.getBody() instanceof ResponseUserDTO) {
            for (Role r : response.getBody().getRoles()) {
                assertEquals("ROLE_ADMIN", r.getRole());
            }
        }
    }
}
