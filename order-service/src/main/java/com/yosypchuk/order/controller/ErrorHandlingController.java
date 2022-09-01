package com.yosypchuk.order.controller;

import com.yosypchuk.order.exception.EntityAlreadyExistException;
import com.yosypchuk.order.exception.EntityNotFoundException;
import com.yosypchuk.order.model.ApiError;
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
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException e) {
        log.info("handleEntityNotFoundException: {}", e.getMessage(), e);
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = EntityAlreadyExistException.class)
    public ResponseEntity<ApiError> handleEntityAlreadyExistException(EntityAlreadyExistException e) {
        log.info("handleEntityNotFoundException: {}", e.getMessage(), e);
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, e.getMessage(), e));
    }

    public ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
