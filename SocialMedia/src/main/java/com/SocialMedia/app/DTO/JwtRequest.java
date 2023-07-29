package com.SocialMedia.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtRequest {
    private String login;
    private String password;
}
