package com.dicka.springbootjquerythymeleaf.service;

import com.dicka.springbootjquerythymeleaf.entity.Info;
import com.dicka.springbootjquerythymeleaf.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoServiceImpl implements InfoService{

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public List<Info> listInfo() {
        List<Info> infos = new ArrayList<>();
        for (Info info : infoRepository.findAll()){
            infos.add(info);
        }
        return infos;
    }
}
