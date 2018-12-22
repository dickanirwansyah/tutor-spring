package com.dicka.commandpattern.repository;

import com.dicka.commandpattern.entity.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PenggunaRepository extends JpaRepository<Pengguna, String>{

    List<Pengguna> findByAktiv(String aktiv);

}
