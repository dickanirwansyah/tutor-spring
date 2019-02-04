package com.dicka.springbootjquerythymeleaf.repository;

import com.dicka.springbootjquerythymeleaf.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Integer> {
}
