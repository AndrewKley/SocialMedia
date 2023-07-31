package com.SocialMedia.app.rest.controller;

import com.SocialMedia.app.DTO.ResponseUserDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void findAllUsers_ReturnValidResponseEntity() {
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var response = testRestTemplate.exchange("/api/users", HttpMethod.GET, new HttpEntity<>(headers), Iterable.class);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
   }

    @Test
    void findUserByLogin_LoginFound_ReturnValidResponseEntity() {
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        var response = testRestTemplate.exchange("/api/users/{ark}", HttpMethod.GET, new HttpEntity<>(headers), ResponseUserDTO.class, "ark");

        assertNotNull(response);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
    }
}
