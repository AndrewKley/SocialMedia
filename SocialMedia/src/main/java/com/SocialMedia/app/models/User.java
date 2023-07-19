package com.SocialMedia.app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table(name = "users")
public class User {
    @Id
    @NotNull
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String login;
    @NotNull
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @NotNull
    private String role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Note> notes;
}
