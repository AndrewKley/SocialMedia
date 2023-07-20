package com.SocialMedia.app.services;

import com.SocialMedia.app.exceptions.PostNotFoundException;
import com.SocialMedia.app.models.Post;
import com.SocialMedia.app.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Iterable<Post> findAll() {
        return repository.findAll();
    }

    public Optional<Post> findPost(Post Post) {
        return repository.findById(Post.getId());
    }

    public Post addPost(Post Post) {
        repository.save(Post);
        return Post;
    }

    public Post deletePost(Post Post) throws PostNotFoundException {
        Optional<Post> resPost = repository.findById(Post.getId());
        if (!resPost.isPresent()) {
            throw new PostNotFoundException();
        }
        repository.deleteById(resPost.get().getId());
        return resPost.get();
    }

    public Post updatePost(Post Post) throws PostNotFoundException {
        deletePost(Post);
        addPost(Post);
        return Post;
    }
}
