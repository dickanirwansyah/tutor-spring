package com.dicka.jpaentitymanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotfound extends RuntimeException{

    public ResourceNotfound(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotfound(String message){
        super(message);
    }
}
