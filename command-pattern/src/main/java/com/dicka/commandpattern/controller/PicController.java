package com.dicka.commandpattern.controller;

import com.dicka.commandpattern.commandPattern.CreateNewPicCommand;
import com.dicka.commandpattern.commandPattern.ServiceExecutor;
import com.dicka.commandpattern.entity.Pic;
import com.dicka.commandpattern.model.PicRequest;
import com.dicka.commandpattern.repository.PicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/pic")
public class PicController {

    private final ServiceExecutor serviceExecutor;
    private Map<String, String> errorValidation;
    private final PicRepository picRepository;

    @Autowired
    public PicController(ServiceExecutor serviceExecutor,
                         PicRepository picRepository){
        this.serviceExecutor = serviceExecutor;
        this.picRepository = picRepository;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Pic>> getAllPics(){
        List<Pic> listPics = new ArrayList<>();
        for (Pic pic : picRepository.findAll()){
            listPics.add(pic);
        }
        return new ResponseEntity<List<Pic>>(listPics, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewPic(@RequestBody @Valid PicRequest request,
                                       BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            errorValidation = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                errorValidation.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errorValidation, HttpStatus.NOT_ACCEPTABLE);
        }

        PicRequest picRequest = PicRequest
                .builder()
                .picName(request.getPicName())
                .picPosition(request.getPicPosition())
                .build();

        serviceExecutor.execute(CreateNewPicCommand.class, picRequest);

        return new ResponseEntity<Object>(picRequest, HttpStatus.CREATED);
    }
}
