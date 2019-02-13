package com.dicka.jpaentitymanager.controller;

import com.dicka.jpaentitymanager.entity.Pengguna;
import com.dicka.jpaentitymanager.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/pengguna")
public class PenggunaController {

    @Autowired
    private PenggunaService penggunaService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Pengguna> listPengguna(){
        return penggunaService.findAllPengguna();
    }

    @RequestMapping(value = "/data")
    public List<Pengguna> listPenggunaByGoldarah(@RequestParam("goldarah")String goldarah){
        return penggunaService.findPenggunaByGoldarah(goldarah);
    }

    @RequestMapping(value = "/data/email/{email}")
    public ResponseEntity<Pengguna> findByEmail(@PathVariable("email")String email){
        Pengguna pengguna = penggunaService.findByEmail(email);
        if (pengguna == null){
            return new ResponseEntity<Pengguna>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pengguna>(pengguna, HttpStatus.OK);
    }

    @RequestMapping(value = "/create")
    public ResponseEntity<Pengguna> createPengguna(@RequestBody Pengguna pengguna){
        Pengguna existByEmail = penggunaService.findByEmail(pengguna.getEmail());
        if (existByEmail != null){
            return new ResponseEntity<Pengguna>(HttpStatus.CONFLICT);
        }

        Pengguna result = penggunaService.createPengguna(pengguna);
        return new ResponseEntity<Pengguna>(result, HttpStatus.OK);
    }
}
