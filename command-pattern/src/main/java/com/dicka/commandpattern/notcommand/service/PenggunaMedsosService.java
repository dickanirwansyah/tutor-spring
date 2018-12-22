package com.dicka.commandpattern.notcommand.service;

import com.dicka.commandpattern.entity.Medsos;
import com.dicka.commandpattern.entity.Pengguna;
import com.dicka.commandpattern.entity.PenggunaMedsos;
import com.dicka.commandpattern.exception.ResourceNotFoundException;
import com.dicka.commandpattern.notcommand.request.PenggunaMedsosRequestNotCommand;
import com.dicka.commandpattern.notcommand.response.PenggunaMedsosResponse;
import com.dicka.commandpattern.notcommand.response.ServiceResponse;
import com.dicka.commandpattern.repository.MedsosRepository;
import com.dicka.commandpattern.repository.PenggunaMedsosRepository;
import com.dicka.commandpattern.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PenggunaMedsosService {

    private final PenggunaMedsosRepository penggunaMedsosRepository;
    private final PenggunaRepository penggunaRepository;
    private final MedsosRepository medsosRepository;

    @Autowired
    public PenggunaMedsosService(PenggunaMedsosRepository penggunaMedsosRepository,
                                 PenggunaRepository penggunaRepository,
                                 MedsosRepository medsosRepository){

        this.penggunaMedsosRepository = penggunaMedsosRepository;
        this.penggunaRepository = penggunaRepository;
        this.medsosRepository = medsosRepository;
    }

    /** list medsos berdasarkan email pengguna **/
    public PenggunaMedsosResponse responseMedsosByEmail(String email){
        /** check email pengguna **/
        Pengguna pengguna = penggunaRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("email : " +email+ " not found."));

        List<Medsos> medsosByEmail = new ArrayList<>();
        for (Medsos medsos : medsosRepository.findMedsosByEmail(email)){
            medsosByEmail.add(medsos);
        }

        PenggunaMedsosResponse response = PenggunaMedsosResponse
                .builder()
                .data1(pengguna)
                .data2(medsosByEmail)
                .build();

        return response;
    }

    public ServiceResponse createPenggunaMedsos(String email, List<String> listMedsosId){

        PenggunaMedsos penggunaMedsos = null;

        for (String medsosId : listMedsosId){
            penggunaMedsos = new PenggunaMedsos();
            penggunaMedsos.getPenggunaMedsosPK().setEmail(email);
            penggunaMedsos.getPenggunaMedsosPK().setMedsosId(medsosId);
            penggunaMedsos.setCreatedAt(new Date());
            penggunaMedsosRepository.save(penggunaMedsos);
        }

        return new ServiceResponse("succes", penggunaMedsos);
    }
}
