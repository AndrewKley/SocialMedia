package com.SocialMedia.app.controllers;

import com.SocialMedia.app.models.Post;
import com.SocialMedia.app.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// curl -v -X GET http://localhost:8080/users/Post
// curl -v -X POST http://localhost:8080/users/Post -d '{"header": "Cart", "body": "Buy a chocolate, a car", "user": {"login": "arkadiy", "password": "123456", "role": "ADMIN"}}' -H "Content-Type: application/json"

@RestController
@RequestMapping(value = "api/users/post", produces = "application/json")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public Iterable<Post> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Post addPost(@RequestBody Post Post) {
        // check user exists
        service.addPost(Post);
        return Post;
    }
}
