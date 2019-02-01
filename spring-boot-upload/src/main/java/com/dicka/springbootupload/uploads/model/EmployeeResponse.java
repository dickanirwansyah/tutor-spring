package com.dicka.springbootupload.uploads.model;

public class EmployeeResponse {

    private String code;
    private String msg;

    public EmployeeResponse(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }
}
