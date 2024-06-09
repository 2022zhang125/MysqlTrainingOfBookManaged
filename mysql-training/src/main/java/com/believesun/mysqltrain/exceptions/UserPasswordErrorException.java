package com.believesun.mysqltrain.exceptions;

public class UserPasswordErrorException extends Exception{
    public UserPasswordErrorException() {
        super();
    }

    public UserPasswordErrorException(String message) {
        super(message);
    }
}
