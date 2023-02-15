package com.sametibis.departmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceAlreadyExistException extends RuntimeException {
    private String resource;
    private String fieldName;
    private String fieldValue;
    public ResourceAlreadyExistException(String resource, String fieldName, String fieldValue) {
        super(String.format("%s is already exist with %s: %s", resource, fieldName, fieldValue));
        this.resource = resource;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
