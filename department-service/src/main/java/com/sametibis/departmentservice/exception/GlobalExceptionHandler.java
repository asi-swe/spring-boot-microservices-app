package com.sametibis.departmentservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public GlobalExceptionHandler() {
        System.out.println("created");
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        System.out.println("HERE");
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "DEPARTMENT_NOT_FOUND",
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        System.out.println(errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleResourceAlreadyExistException(ResourceAlreadyExistException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "RESOURCE_ALREADY_EXIST",
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        System.out.println(errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


}
