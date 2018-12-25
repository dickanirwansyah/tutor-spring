package com.dicka.springbootexamplejsp.service;

import java.util.Map;

import com.dicka.springbootexamplejsp.entity.Order;

public interface OrderRepository {

	public void putOrder(Order order);
	public Order getOrder(String orderId);
	public Map<String, Order> getAllOrders();
}
