package com.dicka.commandpattern.notcommand.controller;

import com.dicka.commandpattern.notcommand.request.PenggunaMedsosRequestNotCommand;
import com.dicka.commandpattern.notcommand.response.PenggunaMedsosResponse;
import com.dicka.commandpattern.notcommand.response.ServiceResponse;
import com.dicka.commandpattern.notcommand.service.PenggunaMedsosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/pengguna-medsos")
public class PenggunaMedsosApi {

    private final PenggunaMedsosService penggunaMedsosService;
    private Map<String, String> errorsValidation;

    @Autowired
    public PenggunaMedsosApi(PenggunaMedsosService penggunaMedsosService){
        this.penggunaMedsosService = penggunaMedsosService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createPenggunaMedsos(@RequestBody @Valid PenggunaMedsosRequestNotCommand request,
                                                       BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            errorsValidation = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                errorsValidation.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errorsValidation, HttpStatus.NOT_ACCEPTABLE);
        }

        if (request.getMedsosId() == null || request.getMedsosId().isEmpty()){
            return new ResponseEntity<Object>("medsos id kosong", HttpStatus.NOT_ACCEPTABLE);
        }

        ServiceResponse response = this.penggunaMedsosService.createPenggunaMedsos(
                request.getEmail(), request.getMedsosId());
        return new ResponseEntity<Object>(request, HttpStatus.CREATED);
    }

    @GetMapping(value = "/by")
    public ResponseEntity<PenggunaMedsosResponse> findMedsosByEmail(@RequestParam("email") String email){
        PenggunaMedsosResponse response = this.penggunaMedsosService
                .responseMedsosByEmail(email);

        if (response == null){
            return new ResponseEntity<PenggunaMedsosResponse>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<PenggunaMedsosResponse>(response, HttpStatus.OK);
    }
}
