package com.dicka.commandpattern.notcommand.service;

import com.dicka.commandpattern.entity.Medsos;
import com.dicka.commandpattern.notcommand.request.MedsosRequestNotCommand;
import com.dicka.commandpattern.notcommand.response.ServiceResponse;
import com.dicka.commandpattern.repository.MedsosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedsosService {

    private final MedsosRepository medsosRepository;

    @Autowired
    public MedsosService(MedsosRepository medsosRepository){
        this.medsosRepository = medsosRepository;
    }

    public List<Medsos> listMedsos(){
       List<Medsos> listMedsos = new ArrayList<>();
       for (Medsos medsos : medsosRepository.findAll()){
           listMedsos.add(medsos);
       }
       return listMedsos;
    }


    public ServiceResponse createNewMedsos(MedsosRequestNotCommand request){

        Medsos medsos = Medsos
                .builder()
                .medsosId(request.getMedsosId())
                .nama(request.getNama())
                .link(request.getLink())
                .build();

        medsosRepository.save(medsos);

        return new ServiceResponse("success",  medsos);
    }

}
