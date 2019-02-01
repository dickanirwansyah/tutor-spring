package com.dicka.springbootupload.uploads;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
//@Data
//@Builder
public class Employee implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String designation;
    @Column(name = "profile_pic_path")
    private String profilePicPath;

    public Employee(){}

    public Employee(String name, String designation, String profilePicPath){
        this.name = name;
        this.designation = designation;
        this.profilePicPath = profilePicPath;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDesignation(){
        return designation;
    }

    public void setDesignation(String designation){
        this.designation = designation;
    }

    public String getProfilePicPath(){
        return profilePicPath;
    }

    public void setProfilePicPath(String profilePicPath){
        this.profilePicPath = profilePicPath;
    }
}
