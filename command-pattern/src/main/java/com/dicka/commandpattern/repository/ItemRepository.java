package com.dicka.commandpattern.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicka.commandpattern.entity.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>{

    Item findItemById(Long id);
    List<Item> findItemByPrice(double price);

}
