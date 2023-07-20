package com.SocialMedia.app.repositories;

import com.SocialMedia.app.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
