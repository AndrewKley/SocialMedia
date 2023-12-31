package com.SocialMedia.app.DTO;

import com.SocialMedia.app.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
public class ResponseUserDTO {
    private String login;
    private Set<Role> roles;
}
