package com.dicka.junittestexample.exception;


public class PenggunaException extends Exception{

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public PenggunaException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public PenggunaException(){
        super();
    }

}
