package com.SocialMedia.app.repositories;

import com.SocialMedia.app.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByLogin(String login);
}
