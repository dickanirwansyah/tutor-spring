package com.dicka.springbootjquerythymeleaf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String msg){
        super(msg);
    }

    public ResourceNotFound(String msg, Throwable cause){
        super(msg, cause);
    }
}
