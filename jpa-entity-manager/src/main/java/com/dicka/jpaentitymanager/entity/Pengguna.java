package com.dicka.jpaentitymanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name = "tabel_pengguna")
public class Pengguna implements Serializable{

    @Id
    @Column(name = "pengguna_id", unique = true)
    private String penggunaId;
    private String nama;
    private String alamat;
    private String telepon;

    @Email(message = "email tidak valid")
    private String email;
    private String goldarah;

    public Pengguna(){}

    public Pengguna(String penggunaId, String nama, String alamat, String telepon, String goldarah){
        this.penggunaId = penggunaId;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
        this.goldarah = goldarah;
    }

    public String getPenggunaId(){
        return penggunaId;
    }

    public void setPenggunaId(String penggunaId){
        this.penggunaId = penggunaId;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getAlamat(){
        return alamat;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public String getTelepon(){
        return telepon;
    }

    public void setTelepon(String telepon){
        this.telepon = telepon;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getGoldarah(){
        return goldarah;
    }

    public void setGoldarah(String goldarah){
        this.goldarah = goldarah;
    }
}
