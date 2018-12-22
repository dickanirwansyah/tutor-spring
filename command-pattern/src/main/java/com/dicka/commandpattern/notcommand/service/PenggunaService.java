package com.dicka.commandpattern.notcommand.service;

import com.dicka.commandpattern.entity.Pengguna;
import com.dicka.commandpattern.notcommand.request.PenggunaRequestNotCommand;
import com.dicka.commandpattern.notcommand.response.ServiceResponse;
import com.dicka.commandpattern.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PenggunaService {

    private final PenggunaRepository penggunaRepository;

    @Autowired
    public PenggunaService(PenggunaRepository penggunaRepository){
        this.penggunaRepository = penggunaRepository;
    }


    public List<Pengguna> listPenggunaAktiv(String aktiv){
        List<Pengguna> listPenggunaAktivs = new ArrayList<>();
        for (Pengguna pengguna : penggunaRepository.findByAktiv(aktiv)){
            listPenggunaAktivs.add(pengguna);
        }
        return listPenggunaAktivs;
    }

    public List<Pengguna> listPengguna(){
        List<Pengguna> listPengguna = new ArrayList<>();
        for (Pengguna pengguna: penggunaRepository.findAll()){
            listPengguna.add(pengguna);
        }
        return listPengguna;
    }

    public ServiceResponse createPengguna(PenggunaRequestNotCommand request){

        Pengguna pengguna = Pengguna
                .builder()
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .aktiv("v")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        penggunaRepository.save(pengguna);
        return new ServiceResponse("success", pengguna);
    }

}
