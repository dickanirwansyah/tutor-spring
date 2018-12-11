package com.dicka.springjunctiontable.model;

import com.dicka.springjunctiontable.entity.Person;

import java.util.Date;

public class ResponsePerson {

    private String msg;
    private String code;
    private Person person;

    public ResponsePerson(String msg, String code, Person person){
        this.msg = msg;
        this.code = code;
        this.person = person;
    }

    public Person getPerson(){
        return person;
    }

    public String getMsg(){
        return msg;
    }

    public String getCode(){
        return code;
    }
}
