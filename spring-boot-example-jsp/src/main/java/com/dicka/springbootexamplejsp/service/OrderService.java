package com.dicka.springbootexamplejsp.service;

import java.util.Map;

import com.dicka.springbootexamplejsp.entity.InventoryResponse;
import com.dicka.springbootexamplejsp.entity.Order;

public interface OrderService {

	public void sendOrder(Order order);
	public void updateOrder(InventoryResponse response);
	public Map<String, Order> getAllOrders();
	
}
