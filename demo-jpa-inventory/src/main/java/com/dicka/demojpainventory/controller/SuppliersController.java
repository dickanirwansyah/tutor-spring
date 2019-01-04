package com.dicka.demojpainventory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicka.demojpainventory.commandpattern.AddNewSuppliersCommand;
import com.dicka.demojpainventory.commandpattern.UpdateSupplierCommand;
import com.dicka.demojpainventory.entity.Suppliers;
import com.dicka.demojpainventory.model.Response;
import com.dicka.demojpainventory.repository.SuppliersRepository;
import com.dicka.demojpainventory.request.AddNewSuppliersRequest;
import com.dicka.demojpainventory.request.UpdateSuppliersRequest;
import com.dicka.demojpainventory.service.ServiceExecutor;

@RestController
@RequestMapping(value = "/api/v1/suppliers")
public class SuppliersController {

	private final ServiceExecutor executor;
	private final SuppliersRepository repository;
	private Map<String, String> validations;
	
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
	
	@PostMapping(value="/update/{id}")
	public ResponseEntity<Object> updateSuppliers(@RequestBody @Valid UpdateSuppliersRequest request,
			@PathVariable("id") int id,
			BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			validations = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()){
				validations.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Object>(validations, HttpStatus.NOT_ACCEPTABLE);
		}
		
		UpdateSuppliersRequest requestSuppliers = UpdateSuppliersRequest
				.builder()
				.id(id)
				.name(request.getName())
				.address(request.getAddress())
				.email(request.getEmail())
				.phone(request.getPhone())
				.build();
		
		executor.execute(UpdateSupplierCommand.class, requestSuppliers);
		return new ResponseEntity<Object>(requestSuppliers, HttpStatus.OK);
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
