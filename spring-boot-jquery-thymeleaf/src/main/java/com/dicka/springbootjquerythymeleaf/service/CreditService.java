package com.dicka.springbootjquerythymeleaf.service;

import com.dicka.springbootjquerythymeleaf.entity.Credit;
import com.dicka.springbootjquerythymeleaf.request.CreditRequest;

import java.util.List;

public interface CreditService {

    List<Credit> listInfo();
    //Credit createdCredit(CreditRequest creditRequest);
    Credit newCredit(Credit credit, String infoId);
}
