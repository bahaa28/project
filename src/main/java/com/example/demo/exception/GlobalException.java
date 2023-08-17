package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundEcxeption.class)
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(
            ResourceNotFoundEcxeption ex, WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(), new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorObject> handleValidationException(
            ValidationException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

}
