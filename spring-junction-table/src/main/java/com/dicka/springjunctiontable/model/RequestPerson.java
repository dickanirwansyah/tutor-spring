package com.dicka.springjunctiontable.model;

public class RequestPerson {

    private String email;
    private String nama;
    private String phone;

    public RequestPerson(){}

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}
