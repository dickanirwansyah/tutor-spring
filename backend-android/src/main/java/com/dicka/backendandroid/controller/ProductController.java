package com.dicka.backendandroid.controller;

import com.dicka.backendandroid.entity.Product;
import com.dicka.backendandroid.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> products = new ArrayList<>();
        List<Product> listProduct= productRepository.findAll();
        for (Product product : listProduct){
            products.add(product);
        }

        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Product>> getProductId(@PathVariable("id")Long id){
        Optional<Product> productId = productRepository.findById(id);
        if (!productId.isPresent()){
            return new ResponseEntity<Optional<Product>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Product>>(productId, HttpStatus.OK);
    }
}
