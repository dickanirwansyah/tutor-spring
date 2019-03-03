package com.dicka.jpaentitymanager.service;

import com.dicka.jpaentitymanager.entity.Produk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//produk repository
public interface ProdukService extends JpaRepository<Produk, Long>{

    List<Produk> findProdukByJumlah(int jumlah);
    Produk findProdukByProdukId(Long produkId);
}
