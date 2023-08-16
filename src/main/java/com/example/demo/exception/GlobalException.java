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
    public ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundEcxeption resourceNotFoundEcxeption,
                                                            WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(resourceNotFoundEcxeption.getMessage());
        errorObject.setTimeStamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }
}
