package com.SocialMedia.app.DTO;

import com.SocialMedia.app.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Data
public class ResponseUserDTO {
    private String login;
    private List<Role> roles;
}
