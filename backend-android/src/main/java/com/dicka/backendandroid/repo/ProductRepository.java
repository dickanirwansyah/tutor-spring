package com.dicka.backendandroid.repo;

import com.dicka.backendandroid.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
