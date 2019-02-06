package com.dicka.springbootjquerythymeleaf.controller;

import com.dicka.springbootjquerythymeleaf.entity.Credit;
import com.dicka.springbootjquerythymeleaf.request.CreditRequest;
import com.dicka.springbootjquerythymeleaf.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping
    public List<Credit> credits(){
        return creditService.listInfo();
    }

    @GetMapping(value = "/{creditId}")
    public Optional<Credit> findById(@PathVariable("creditId") Integer creditId){
        return creditService.findCreditId(creditId);
    }

    @PutMapping(value = "/info/{infoId}/credit/{creditId}/update")
    public Credit updateCredit(@PathVariable(value = "infoId") String infoId,
                               @PathVariable(value = "creditId") Integer creditId,
                               @RequestBody Credit credit){

        return creditService.updateCredit(credit, infoId, creditId);
    }

    /**
    @PostMapping
    public Credit newCredit(CreditRequest creditRequest){
        return creditService.createdCredit(creditRequest);
    }
     **/

    @PostMapping(value = "/create/{infoId}")
    public Credit createCredit(@PathVariable("infoId")String infoId,
                               @RequestBody Credit credit){
        return creditService.newCredit(credit, infoId);
    }
}
