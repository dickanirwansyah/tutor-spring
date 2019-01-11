package com.dicka.commandpattern.repository.sample;

import com.dicka.commandpattern.entity.sample.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
