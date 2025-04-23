package org.flechaamarilla.exception;

public class UsernameAlreadyExistsException extends UserAlreadyExistsException {
    public UsernameAlreadyExistsException() {
        super("A user with this username already exists");
    }
}