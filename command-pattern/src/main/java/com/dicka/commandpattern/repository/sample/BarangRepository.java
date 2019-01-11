package com.dicka.commandpattern.repository.sample;

import com.dicka.commandpattern.entity.sample.Barang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarangRepository extends JpaRepository<Barang, String> {

    List<Barang> findByPrice(double price);
}
