package com.SocialMedia.app.DTO;

import com.SocialMedia.app.models.Post;
import com.SocialMedia.app.models.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationUserDTO {
    private String login;
    private String password;
    private String confirmPassword;
}
