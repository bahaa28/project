package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundEcxeption extends RuntimeException{

    public ResourceNotFoundEcxeption(String message){
        super(message);
    }

    public ResourceNotFoundEcxeption(String s, Throwable throwable) {
        super(s, throwable);
    }
}
