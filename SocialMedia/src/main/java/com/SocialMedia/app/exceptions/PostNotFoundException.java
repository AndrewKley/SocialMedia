package com.SocialMedia.app.exceptions;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public PostNotFoundException() { }
}
