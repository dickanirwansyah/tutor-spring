package com.dicka.springbootangularjs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicka.springbootangularjs.entity.Customer;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

	List<Customer> custs = new ArrayList<Customer>();
	
	@GetMapping(value = "/all")
	public List<Customer> getResource(){
		return custs;
	}
	
	@PostMapping(value = "/save")
	public String postCustomer(@RequestBody List<Customer> customers){
		custs.addAll(customers);
		return "successfully";
	}
}
