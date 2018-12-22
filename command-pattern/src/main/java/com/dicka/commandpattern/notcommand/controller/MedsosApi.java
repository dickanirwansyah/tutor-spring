package com.dicka.commandpattern.notcommand.controller;

import com.dicka.commandpattern.entity.Medsos;
import com.dicka.commandpattern.notcommand.request.MedsosRequestNotCommand;
import com.dicka.commandpattern.notcommand.response.ServiceResponse;
import com.dicka.commandpattern.notcommand.service.MedsosService;
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
@RequestMapping(value = "/api/v1/medsos")
public class MedsosApi {

    private final MedsosService medsosService;
    private Map<String, String> errorsValidation;

    @Autowired
    public MedsosApi(MedsosService medsosService){
        this.medsosService = medsosService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createMedsos(@RequestBody @Valid MedsosRequestNotCommand request,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            errorsValidation = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                errorsValidation.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errorsValidation, HttpStatus.NOT_ACCEPTABLE);
        }

        ServiceResponse response = this.medsosService.createNewMedsos(request);
        return new ResponseEntity<Object>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Medsos>> listMedsos(){
        List<Medsos> medsos = this.medsosService.listMedsos();
        if (medsos.isEmpty()){
            return new ResponseEntity<List<Medsos>>(HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<List<Medsos>>(medsos, HttpStatus.OK);
        }
    }
}
