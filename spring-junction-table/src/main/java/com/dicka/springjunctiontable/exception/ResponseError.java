package com.dicka.springjunctiontable.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ResponseError extends RuntimeException{

    private String msg;
    private String code;
    private Map<String, String> errors;

    public ResponseError(){}

    public ResponseError(String msg, String code, Map<String, String> errors){
        this.msg = msg;
        this.code = code;
        this.errors = errors;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public Map<String,String> getErrors(){
        return errors;
    }

    public void setErrors(Map<String, String> errors){
        this.errors = errors;
    }
}
