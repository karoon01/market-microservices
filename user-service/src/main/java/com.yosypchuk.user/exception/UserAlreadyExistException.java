package com.yosypchuk.user.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public UserAlreadyExistException(String message){
        super(message);
    }
}

