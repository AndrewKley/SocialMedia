package com.SocialMedia.app.services;

import com.SocialMedia.app.exceptions.UserNotFoundException;
import com.SocialMedia.app.models.Post;
import com.SocialMedia.app.models.User;
import com.SocialMedia.app.repositories.PostRepository;
import com.SocialMedia.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository repository;
    private RoleService roleService;

    @Autowired
    public UserService(UserRepository repository, RoleService roleService) {
        this.repository = repository;
        this.roleService = roleService;
    }

    public Iterable<User> findAllUser() {
        return repository.findAll();
    }

    public Optional<User> findUserByLogin(String login) {
        return repository.findByLogin(login);
    }

    public User saveUser(User user) {
        repository.save(user);
        return user;
    }

    public User addUser(User user) throws UserNotFoundException {
        repository.save(user);
        return user;
    }

    public User deleteUser(User user) {
        Optional<User> resUser = repository.findByLogin(user.getLogin());
        if (resUser.isPresent()) {
            repository.delete(user);
            return resUser.get();
        }
        return null;
    }

    public User updateUser(User user) throws UserNotFoundException {
        deleteUser(user);
        addUser(user);
        return user;
    }

    public Post addpostByUser(User user, Post post) throws UserNotFoundException {
        Optional<User> resUser = repository.findById(user.getLogin());
        if (!resUser.isPresent()) {
            throw new UserNotFoundException();
        }
        resUser.get().getNotes().add(post);
        return post;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public void createUser(User user) throws Exception {
        if (repository.findByLogin(user.getLogin()).isPresent()) {
            throw new Exception("User with this login exists");
        }
        user.setRoles(List.of(roleService.findByName("ROLE_USER").get()));
        repository.save(user);
    }
}
