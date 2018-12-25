package com.dicka.springbootexamplejsp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dicka.springbootexamplejsp.entity.Order;
import com.dicka.springbootexamplejsp.service.OrderService;

@Controller
public class AppController {

	private final OrderService orderService;
	
	@Autowired
	public AppController(OrderService orderService){
		this.orderService = orderService;
	}
	
	@GetMapping(value = {"/", "/home"})
	public String getIndex(ModelMap map){
		return "Index";
	}
	
	@GetMapping(value = "/newOrder")
	public String prepareOrder(ModelMap map){
		Order order = new Order();
		map.addAttribute("order", order);
		return "Order";
	}
	
	@PostMapping(value = "/newOrder")
	public String sendOrder(@Valid Order order, 
			BindingResult bindingResult, ModelMap map){
		
		if(bindingResult.hasErrors()){
			return "Order";
		}
		orderService.sendOrder(order);
		map.addAttribute("success", "Order for "+order.getProductName()+" registered.");
		return "OrderSuccess";
	}
	
	@GetMapping(value = "/checkStatus")
	public String checkOrderStatus(ModelMap map){
		map.addAttribute("orders", orderService.getAllOrders());
		return "OrderSuccess";
	}
}
