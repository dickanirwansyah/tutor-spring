package com.dicka.commandpattern.notcommand.controller;

import com.dicka.commandpattern.entity.Pengguna;
import com.dicka.commandpattern.notcommand.request.PenggunaRequestNotCommand;
import com.dicka.commandpattern.notcommand.response.ServiceResponse;
import com.dicka.commandpattern.notcommand.service.PenggunaService;
import com.dicka.commandpattern.repository.PenggunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/pengguna")
public class PenggunaApi {

    private final PenggunaService penggunaService;
    private Map<String, String> errorsValidation;
    private final PenggunaRepository penggunaRepository;

    @Autowired
    public PenggunaApi(PenggunaService penggunaService,
                       PenggunaRepository penggunaRepository){
        this.penggunaService = penggunaService;
        this.penggunaRepository = penggunaRepository;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Pengguna>> listPengguna(){
        List<Pengguna> penggunaList = this.penggunaService.listPengguna();
        if (penggunaList.isEmpty()){
            return new ResponseEntity<List<Pengguna>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Pengguna>>(penggunaList, HttpStatus.OK);
    }

    @GetMapping(value = "/list-aktiv")
    public ResponseEntity<List<Pengguna>> listPenggunaAktiv(@RequestParam("aktiv")
                                                            String aktiv){
        List<Pengguna> penggunaListAktiv = this.penggunaService.listPenggunaAktiv(aktiv);
        if (penggunaListAktiv.isEmpty()){
            return new ResponseEntity<List<Pengguna>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Pengguna>>(penggunaListAktiv, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createPengguna(@RequestBody @Valid PenggunaRequestNotCommand request,
                                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            errorsValidation = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                errorsValidation.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errorsValidation, HttpStatus.NOT_ACCEPTABLE);
        }

        if (penggunaRepository.findById(request.getEmail()).isPresent()){
            return new ResponseEntity<Object>(HttpStatus.CONFLICT);
        }

        ServiceResponse response = this.penggunaService.createPengguna(request);
        return new ResponseEntity<Object>(request, HttpStatus.CREATED);
    }
}
