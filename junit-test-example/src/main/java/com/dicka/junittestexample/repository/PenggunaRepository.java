package com.dicka.junittestexample.repository;

import com.dicka.junittestexample.entity.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenggunaRepository extends JpaRepository<Pengguna, String> {
}
