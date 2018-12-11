package com.dicka.springjunctiontable.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String msg){
        super(msg);
    }

    public InternalServerErrorException(String msg, Throwable cause){
        super(msg, cause);
    }
}
