package com.SocialMedia.app.DTO;

import com.SocialMedia.app.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class RegistrationUserDTO {
    private String login;
    private String password;
    private String confirmPassword;
    private Set<Role> roles;
}
