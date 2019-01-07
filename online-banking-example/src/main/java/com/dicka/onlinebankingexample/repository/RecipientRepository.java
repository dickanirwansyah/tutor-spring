package com.dicka.onlinebankingexample.repository;

import com.dicka.onlinebankingexample.entity.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {

    List<Recipient> findAll();

    Recipient findByName(String name);

    void deleteByName(String name);
}
