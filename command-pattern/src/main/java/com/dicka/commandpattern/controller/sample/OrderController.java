package com.dicka.commandpattern.controller.sample;

import com.dicka.commandpattern.entity.sample.Order;
import com.dicka.commandpattern.exception.ResourceNotFoundException;
import com.dicka.commandpattern.repository.sample.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @GetMapping(value = "/{id}")
    public Order findById(@PathVariable("id") Long id){
        return orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("id notfound"));
    }
}
