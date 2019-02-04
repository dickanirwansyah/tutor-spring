package com.dicka.springbootjquerythymeleaf.controller;

import com.dicka.springbootjquerythymeleaf.entity.Info;
import com.dicka.springbootjquerythymeleaf.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping()
    public List<Info> getInfos(){
        return infoService.listInfo();
    }
}
