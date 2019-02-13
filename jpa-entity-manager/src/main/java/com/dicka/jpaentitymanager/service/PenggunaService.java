package com.dicka.jpaentitymanager.service;

import com.dicka.jpaentitymanager.entity.Pengguna;

import java.util.List;

public interface PenggunaService {

    List<Pengguna> findAllPengguna();
    List<Pengguna> findPenggunaByGoldarah(String goldarah);
    Pengguna createPengguna(Pengguna pengguna);
    Pengguna findByEmail(String email);
}
