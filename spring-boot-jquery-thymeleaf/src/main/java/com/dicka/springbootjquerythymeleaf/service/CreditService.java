package com.dicka.springbootjquerythymeleaf.service;

import com.dicka.springbootjquerythymeleaf.entity.Credit;
import com.dicka.springbootjquerythymeleaf.request.CreditRequest;

import java.util.List;
import java.util.Optional;

public interface CreditService {

    List<Credit> listInfo();
    //Credit createdCredit(CreditRequest creditRequest);
    Credit newCredit(Credit credit, String infoId);
    Credit updateCredit(Credit credit, String infoId, int creditId);
    //cari berdarsarkan id
    Optional<Credit> findCreditId(int creditId);
}
