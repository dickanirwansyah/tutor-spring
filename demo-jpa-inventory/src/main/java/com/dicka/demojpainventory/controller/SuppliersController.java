package com.dicka.demojpainventory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicka.demojpainventory.commandpattern.AddNewSuppliersCommand;
import com.dicka.demojpainventory.entity.Suppliers;
import com.dicka.demojpainventory.model.Response;
import com.dicka.demojpainventory.repository.SuppliersRepository;
import com.dicka.demojpainventory.request.AddNewSuppliersRequest;
import com.dicka.demojpainventory.service.ServiceExecutor;

@RestController
@RequestMapping(value = "/api/v1/suppliers")
public class SuppliersController {

	private final ServiceExecutor executor;
	private final SuppliersRepository repository;
	
	@Autowired
	public SuppliersController(ServiceExecutor executor, SuppliersRepository repository){
		this.executor = executor;
		this.repository = repository;
	}
	
	@GetMapping
	public ResponseEntity<List<Suppliers>> listSuppliers(){
		List<Suppliers> list = new ArrayList<Suppliers>();
		for (Suppliers suppliers : repository.findAll()){
			list.add(suppliers);
		}
		
		if(list.isEmpty()){
			return new ResponseEntity<List<Suppliers>>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Suppliers>>(list, HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/create")
	public Suppliers newSupliers(@RequestBody AddNewSuppliersRequest request){
		AddNewSuppliersRequest requestSuppliers = AddNewSuppliersRequest
				.builder()
				.id(request.getId())
				.name(request.getName())
				.email(request.getEmail())
				.phone(request.getPhone())
				.address(request.getPhone())
				.build();
		
		return executor.execute(AddNewSuppliersCommand.class, 
				requestSuppliers);
	}
}
