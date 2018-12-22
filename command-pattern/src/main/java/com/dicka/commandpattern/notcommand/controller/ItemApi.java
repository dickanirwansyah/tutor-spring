package com.dicka.commandpattern.notcommand.controller;

import com.dicka.commandpattern.model.ItemRequest;
import com.dicka.commandpattern.notcommand.request.ItemRequestNotCommand;
import com.dicka.commandpattern.notcommand.response.ItemResponse;
import com.dicka.commandpattern.notcommand.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/item")
public class ItemApi {

    private final ItemService itemService;
    private Map<String, String> errorsValidation;

    @Autowired
    public ItemApi(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createItem(@RequestBody @Valid ItemRequestNotCommand request,
                                             BindingResult bindingResult){
        ItemResponse response = null;
        if (bindingResult.hasErrors()){
            errorsValidation = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()){
                errorsValidation.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Object>(errorsValidation, HttpStatus.NOT_ACCEPTABLE);
        }else{
            response = itemService.createItem(request);
            return new ResponseEntity<Object>(response, HttpStatus.CREATED);
        }
    }
}
