package com.dicka.jpaentitymanager.controller;

import com.dicka.jpaentitymanager.entity.Produk;
import com.dicka.jpaentitymanager.model.ResponseData;
import com.dicka.jpaentitymanager.service.ProdukServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/produk")
public class ProdukController {

    @Autowired
    private ProdukServiceImpl produkService;

    @GetMapping(value = "/list")
    public List<Produk> getAllProduks(){
        return produkService.getProducts();
    }

    /** save all data **/
    @PostMapping(value = "/save-all")
    public ResponseEntity<ResponseData> createProducts(@RequestBody List<Produk> produks){
        ResponseData data = produkService.saveAllProduk(produks);
        return new ResponseEntity<ResponseData>(data, HttpStatus.CREATED);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Produk> createProduk(@RequestBody Produk produk){
        Produk dataProduk = produkService.createProduk(produk);
        return new ResponseEntity<Produk>(dataProduk, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{produkId}")
    public ResponseEntity<Produk> updateProduk(@RequestBody Produk produk,
                                               @PathVariable("produkId") Long produkId){
        Produk p = produkService.updateProduk(produk, produkId);
        return new ResponseEntity<Produk>(produk, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteProduk(@RequestParam(value = "produkId") Long produkId){
        produkService.deleteProduk(produkId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
