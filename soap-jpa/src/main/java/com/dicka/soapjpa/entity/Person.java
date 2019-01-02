package com.dicka.soapjpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person")
public class Person implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long personId;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;

    public Person(){
    	
    }
    
    public Person(String firstname, String lastname, String email, String phone, String address){
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.email = email;
    	this.phone = phone;
    	this.address = address;
    }
    
    public Long getPersonId(){
        return personId;
    }

    public void setPersonId(Long personId){
        this.personId = personId;
    }
    
    public String getFirstname(){
    	return firstname;
    }
    
    public void setFirstname(String firstname){
    	this.firstname=firstname;
    }
    
    public String getLastname(){
    	return lastname;
    }
    
    public void setLastname(String lastname){
    	this.lastname = lastname;
    }
    
    public String getEmail(){
    	return email;
    }
    
    public void setEmail(String email){
    	this.email = email;
    }
    
    public String getPhone(){
    	return phone;
    }
    
    public void setPhone(String phone){
    	this.phone=phone;
    }
    
    public String getAddress(){
    	return address;
    }
    
    public void setAddress(String address){
    	this.address = address;
    }
}
