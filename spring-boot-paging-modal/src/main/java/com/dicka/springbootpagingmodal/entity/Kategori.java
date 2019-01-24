package com.dicka.springbootpagingmodal.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "kategori")
public class Kategori implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama")
    private String nama;

    public Kategori(){}

    public Kategori(String nama){
        this.nama = nama;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }
}
