package com.dicka.junittestexample.exception;

public class SuccessResponse<T> {

    private int code;
    private String message;
    private T data;

    public SuccessResponse(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }
}
