package com.bachelor.authservice.exception;

import com.bachelor.authservice.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({UserNotFound.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, new HttpResponse("User not found", null, HttpStatus.NOT_FOUND.value()), null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({EmailAlreadyExists.class})
    protected ResponseEntity<Object> handleAlreadyExists(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, new HttpResponse("Email already exists", null, HttpStatus.CONFLICT.value()), null, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({WrongCredentials.class})
    protected ResponseEntity<Object> handleWrongCredentials(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, new HttpResponse("Wrong credentials", null, HttpStatus.CONFLICT.value()),null, HttpStatus.CONFLICT, request);
    }
}
