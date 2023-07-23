package com.SocialMedia.app.DTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String login;
    private String password;
}
