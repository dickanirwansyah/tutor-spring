package com.dicka.springbootexamplejsp.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicka.springbootexamplejsp.entity.InventoryResponse;
import com.dicka.springbootexamplejsp.entity.Order;
import com.dicka.springbootexamplejsp.entity.OrderStatus;
import com.dicka.springbootexamplejsp.message.MessageSender;
import com.dicka.springbootexamplejsp.util.BasicUtils;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
	private final OrderRepository orderRepository;
	private final MessageSender messageSender;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository,
			MessageSender messageSender) {
		
		this.orderRepository = orderRepository;
		this.messageSender = messageSender;
	}
	
	@Override
	public void sendOrder(Order order) {
		LOG.info("------sendOrder()------");
		order.setOrderId(BasicUtils.getUniqueID());
		order.setOrderStatus(OrderStatus.CREATED);
		order.setProductName(order.getProductName());
		orderRepository.putOrder(order);
		LOG.info("Application : sending order request {}", order);
		messageSender.sendMessage(order);
		LOG.info("------sendOrder()-------");
	}

	@Override
	public void updateOrder(InventoryResponse response) {
		Order order = orderRepository.getOrder(response.getOrderId());
		if (response.getReturnCode() == 200){
			order.setOrderStatus(OrderStatus.CREATED);
		}else if(response.getReturnCode() == 300){
			order.setOrderStatus(OrderStatus.FAILED);
		}else{
			order.setOrderStatus(OrderStatus.PENDING);
		}
		orderRepository.putOrder(order);
	}

	@Override
	public Map<String, Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}
}
