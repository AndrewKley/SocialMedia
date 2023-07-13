package org.server.repositories;

import org.server.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends CrudRepository<User, String> {
//    Optional<User> findByLogin(String login);
    UserDetails findByLogin(String login);
}
