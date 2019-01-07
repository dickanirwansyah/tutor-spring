package com.dicka.onlinebankingexample.repository;

import com.dicka.onlinebankingexample.entity.PrimaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryAccountRepository extends JpaRepository<PrimaryAccount, Long>{

    PrimaryAccount findByAccountNumber(int accountNumber);

}
