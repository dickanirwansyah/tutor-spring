package com.dicka.junittestexample.controller;

import com.dicka.junittestexample.exception.ErrorResponse;
import com.dicka.junittestexample.exception.PenggunaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionRestController {

    /** not found **/
    @ExceptionHandler(PenggunaException.class)
    public ResponseEntity<ErrorResponse> handleErrorNotFound(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage("deskripsi error : "+ex.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerErrorBadRequest(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage("deskripsi error : "+ex.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_GATEWAY);
    }

}
