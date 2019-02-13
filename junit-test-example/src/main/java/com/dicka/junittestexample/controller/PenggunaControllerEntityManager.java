package com.dicka.junittestexample.controller;

import com.dicka.junittestexample.entity.Pengguna;
import com.dicka.junittestexample.exception.PenggunaException;
import com.dicka.junittestexample.exception.SuccessResponse;
import com.dicka.junittestexample.repository.PenggunaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PenggunaControllerEntityManager {

    private static final Logger log = LoggerFactory.getLogger(PenggunaControllerEntityManager.class);

    private Map<String, String> mapValidation;

    @Autowired
    private PenggunaService penggunaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Pengguna>> findAll() throws PenggunaException{
        List<Pengguna> penggunas = penggunaService.listPengguna();
        if (penggunas.isEmpty()){
            throw new PenggunaException("data pengguna tidak ada.");
        }

        return new ResponseEntity<List<Pengguna>>(penggunas, HttpStatus.OK);
    }

    @RequestMapping(value = "/cari", method = RequestMethod.POST)
    public ResponseEntity<List<Pengguna>> findPenggunaByNama(@RequestParam(value = "nama") String nama)
        throws PenggunaException{
        List<Pengguna> cariPengguna = penggunaService.searchPenggunaByNama(nama);
        if (cariPengguna.isEmpty()){
            throw new PenggunaException("data pengguna dengan "+nama+" tidak ada di database");
        }

        return new ResponseEntity<List<Pengguna>>(cariPengguna, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createPengguna(@RequestBody @Valid Pengguna pengguna,
                                                          BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            mapValidation = new HashMap<>();
            for (FieldError fieldError: bindingResult.getFieldErrors()){
                mapValidation.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(mapValidation,HttpStatus.BAD_REQUEST);
        }

        SuccessResponse successResponse = penggunaService.createPengguna(pengguna);
        return new ResponseEntity<Object>(successResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/data")
    public ResponseEntity<List<Pengguna>> findByGoldarah(@RequestParam("goldarah")String goldarah)
        throws PenggunaException{
        List<Pengguna> penggunas = penggunaService.findPenggunaByGoldarah(goldarah);
        if (penggunas.isEmpty()){
            throw new PenggunaException("golongan darah "+goldarah+" tidak ada");
        }

        return new ResponseEntity<List<Pengguna>>(penggunas, HttpStatus.OK);
    }
}
