package com.dicka.junittestexample.repository;

import com.dicka.junittestexample.entity.Pengguna;
import com.dicka.junittestexample.exception.SuccessResponse;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PenggunaService {

    List<Pengguna> listPengguna();
    SuccessResponse createPengguna(Pengguna pengguna);
    Pengguna findPenggunaByEmail(String email);
    List<Pengguna> findPenggunaByGoldarah(String goldarah);
    List<Pengguna> searchPenggunaByNama(String nama);

}
