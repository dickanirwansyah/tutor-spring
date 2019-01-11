package com.dicka.commandpattern.controller.sample;

import com.dicka.commandpattern.entity.sample.Barang;
import com.dicka.commandpattern.exception.ResourceNotFoundException;
import com.dicka.commandpattern.repository.sample.BarangRepository;
import com.dicka.commandpattern.repository.sample.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/barang")
public class BarangController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BarangRepository barangRepository;

    @GetMapping
    public ResponseEntity<List<Barang>> listBarang(){
        List<Barang> barangs = new ArrayList<>();
        for (Barang barang : barangRepository.findAll()){
            barangs.add(barang);
        }

        return new ResponseEntity<List<Barang>>(barangs, HttpStatus.OK);
    }

    @PostMapping(value = "/category/{categoryId}")
    public ResponseEntity<Barang> createBarang(@PathVariable("categoryId") Long categoryId,
                                               @RequestBody Barang barang){

        return categoryRepository.findById(categoryId)
                .map(category -> {
                    Barang newBarang = Barang.builder()
                            .barangId(UUID.randomUUID().toString())
                            .name(barang.getName())
                            .price(barang.getPrice())
                            .qty(barang.getQty())
                            .category(category)
                            .build();

                    barangRepository.save(newBarang);
                    return new ResponseEntity<Barang>(newBarang, HttpStatus.CREATED);
                }).orElseThrow(() -> new ResourceNotFoundException(
                        "categoryId not found"
                ));
    }

}
