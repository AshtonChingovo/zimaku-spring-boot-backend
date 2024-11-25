package com.zimaku.zimaku.exception;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(final String message){
        super(message);
    }
}
