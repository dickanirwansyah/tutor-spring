package com.dicka.springbootdemoajax.controller;

import com.dicka.springbootdemoajax.entity.Book;
import com.dicka.springbootdemoajax.response.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/book")
public class ControllerBook {

    private List<Book> books = new ArrayList<>();

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createBook(@RequestBody Book book){
        books.add(book);
        ServiceResponse<Book> response = new ServiceResponse<>("success", book);
        return new ResponseEntity<Object>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<Object> listBook(){
        ServiceResponse<List<Book>> response = new ServiceResponse<>("success", books);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }


}
