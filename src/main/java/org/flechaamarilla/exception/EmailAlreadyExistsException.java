package org.flechaamarilla.exception;

public class EmailAlreadyExistsException extends UserAlreadyExistsException {
    public EmailAlreadyExistsException() {
        super("A user with this email already exists");
    }
}