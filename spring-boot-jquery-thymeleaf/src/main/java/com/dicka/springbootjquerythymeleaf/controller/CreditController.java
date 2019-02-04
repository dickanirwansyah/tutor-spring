package com.dicka.springbootjquerythymeleaf.controller;

import com.dicka.springbootjquerythymeleaf.entity.Credit;
import com.dicka.springbootjquerythymeleaf.request.CreditRequest;
import com.dicka.springbootjquerythymeleaf.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping
    public List<Credit> credits(){
        return creditService.listInfo();
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
