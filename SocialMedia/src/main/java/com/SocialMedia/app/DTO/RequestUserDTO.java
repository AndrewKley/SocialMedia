package com.SocialMedia.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestUserDTO {
    private String login;
    private String password;
}
