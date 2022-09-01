package com.yosypchuk.product.exception;

public class EntityAlreadyExistException extends RuntimeException {

    public EntityAlreadyExistException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
