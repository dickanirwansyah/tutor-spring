package com.dicka.springbootpagingmodal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "country")
public class Country implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String capital;

    public Country(){}

    public Country(String name, String capital){
        this.name = name;
        this.capital = capital;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCapital(){
        return capital;
    }

    public void setCapital(String capital){
        this.capital = capital;
    }
}
