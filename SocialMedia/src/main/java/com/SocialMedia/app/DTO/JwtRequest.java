package com.SocialMedia.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtRequest {
    private String login;
    private String password;

//    public JwtRequest(String login, String password) {
//        this.login = login;
//        this.password = password;
//    }
}
