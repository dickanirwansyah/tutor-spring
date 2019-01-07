package com.dicka.onlinebankingexample.repository;

import com.dicka.onlinebankingexample.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber(int accountNumber);

}
