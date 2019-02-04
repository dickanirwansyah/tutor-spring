package com.dicka.springbootjquerythymeleaf.service;

import com.dicka.springbootjquerythymeleaf.entity.Credit;
import com.dicka.springbootjquerythymeleaf.entity.Info;
import com.dicka.springbootjquerythymeleaf.exception.ResourceNotFound;
import com.dicka.springbootjquerythymeleaf.repository.CreditRepository;
import com.dicka.springbootjquerythymeleaf.repository.InfoRepository;
import com.dicka.springbootjquerythymeleaf.request.CreditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService{

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public List<Credit> listInfo() {
        List<Credit> credits = new ArrayList<>();
        for (Credit credit : creditRepository.findAll()){
            credits.add(credit);
        }
        return credits;
    }

    /** not using path variable
    @Override
    public Credit createdCredit(CreditRequest creditRequest) {
        Credit credit = builderCredit(creditRequest);
        return creditRepository.save(credit);
    }
    **/

    /** using path variable **/
    @Override
    public Credit newCredit(Credit credit, String infoId) {
        return infoRepository.findById(infoId)
                .map(info -> {
                    credit.setName(credit.getName());
                    credit.setCreatedAt(LocalDateTime.now());
                    credit.setUpdatedAt(LocalDateTime.now());
                    credit.setInfo(info);
                    return creditRepository.save(credit);
                }).orElseThrow(() ->
                        new ResourceNotFound("infoId : "+infoId+" notfound."));
    }

}
