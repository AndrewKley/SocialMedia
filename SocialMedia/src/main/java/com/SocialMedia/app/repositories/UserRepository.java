package com.SocialMedia.app.repositories;

import com.SocialMedia.app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
