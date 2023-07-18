package com.SocialMedia.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Note {
    @Id
    @NotNull
    private Long id;
    @NotNull
    private String header;
    @NotNull
    private String body;
    @NotNull
    @OneToOne
    private String user;
}
