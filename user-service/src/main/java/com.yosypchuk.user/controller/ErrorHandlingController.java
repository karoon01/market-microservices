package com.yosypchuk.user.controller;

import com.yosypchuk.user.exception.UserAlreadyExistException;
import com.yosypchuk.user.exception.UserNotFoundException;
import com.yosypchuk.user.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException e) {
        log.info("handleEntityNotFoundException: {}", e.getMessage(), e);
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<ApiError> handleUserAlreadyExistException(UserNotFoundException e) {
        log.info("handleEntityNotFoundException: {}", e.getMessage(), e);
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, e.getMessage(), e));
    }

    public ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
