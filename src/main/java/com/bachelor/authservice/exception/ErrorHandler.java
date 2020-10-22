package com.bachelor.authservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.RollbackException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({UserNotFound.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({EmailAlreadyExists.class})
    protected ResponseEntity<Object> handleAlreadyExists(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), null, HttpStatus.CONFLICT, request);
    }
}
