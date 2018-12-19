package com.dicka.commandpattern.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicka.commandpattern.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
