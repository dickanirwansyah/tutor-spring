package com.dicka.springbootjquerythymeleaf.repository;

import com.dicka.springbootjquerythymeleaf.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, String> {

    Info findByInfoId(String infoId);
}
