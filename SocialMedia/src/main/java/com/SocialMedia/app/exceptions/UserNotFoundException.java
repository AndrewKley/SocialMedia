package com.SocialMedia.app.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public UserNotFoundException() { }
}
