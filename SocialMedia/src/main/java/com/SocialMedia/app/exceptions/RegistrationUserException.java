package com.SocialMedia.app.exceptions;

public class RegistrationUserException extends Exception {
    public RegistrationUserException(String errorMessage) {
        super(errorMessage);
    }

    public RegistrationUserException() { }
}
