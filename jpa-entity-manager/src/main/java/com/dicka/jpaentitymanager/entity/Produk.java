package com.dicka.jpaentitymanager.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tabel_produk")
public class Produk implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produkId;

    private String nama;
    private int jumlah;
    private double harga;

    public Long getProdukId(){
        return produkId;
    }

    public void setProdukId(Long produkId){
        this.produkId = produkId;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
