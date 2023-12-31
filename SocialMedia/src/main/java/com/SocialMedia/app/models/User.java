package com.SocialMedia.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    @Column(name = "login")
    private String login;
    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters long")
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Post> posts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_login"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public User(String login, String password, Set<Role> role) {
        this.login = login;
        this.password = password;
        if (roles == null) {
            roles = role;
        }
    }

    public Role addRole(Role role) {
        if (roles.isEmpty()) {
            roles = new HashSet<>();
        }
        roles.add(role);
        return role;
    }
}
