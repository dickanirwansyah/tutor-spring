package com.dicka.onlinebankingexample.repository;

import com.dicka.onlinebankingexample.entity.PrimaryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrimaryTransactionRepository extends JpaRepository<PrimaryTransaction, Long> {

    List<PrimaryTransaction> findAll();
}
