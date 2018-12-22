package com.dicka.commandpattern.repository;

import com.dicka.commandpattern.entity.Pic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicRepository extends JpaRepository<Pic, Long> {
}
