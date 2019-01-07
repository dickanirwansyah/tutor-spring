package com.dicka.onlinebankingexample.repository;

import com.dicka.onlinebankingexample.entity.SavingsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsTransactionRepository extends JpaRepository<SavingsTransaction, Long> {
}
