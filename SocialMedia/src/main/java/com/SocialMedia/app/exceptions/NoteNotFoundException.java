package com.SocialMedia.app.exceptions;

public class NoteNotFoundException extends Exception {
    public NoteNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NoteNotFoundException() { }
}
