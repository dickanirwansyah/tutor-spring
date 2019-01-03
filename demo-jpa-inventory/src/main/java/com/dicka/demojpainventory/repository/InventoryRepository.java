package com.dicka.demojpainventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dicka.demojpainventory.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, String>{

	List<Inventory> findInventoryByStock(int stock);
	List<Inventory> findInventoryByPrice(double price);
	
}
