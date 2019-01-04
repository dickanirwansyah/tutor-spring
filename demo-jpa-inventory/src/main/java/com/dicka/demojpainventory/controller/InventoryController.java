package com.dicka.demojpainventory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dicka.demojpainventory.commandpattern.AddNewInventoryCommand;
import com.dicka.demojpainventory.commandpattern.UpdateInventoryCommand;
import com.dicka.demojpainventory.exception.ResourceNotFoundException;
import com.dicka.demojpainventory.repository.SuppliersRepository;
import com.dicka.demojpainventory.request.AddNewInventoryRequest;
import com.dicka.demojpainventory.request.UpdateInventoryRequest;
import com.dicka.demojpainventory.service.ServiceExecutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.dicka.demojpainventory.entity.Inventory;
import com.dicka.demojpainventory.repository.InventoryRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/inventory")
public class InventoryController {

	private final InventoryRepository repository;
	private final SuppliersRepository supliersRepo;
	private final ServiceExecutor serviceExecutor;
	private Map<String, String> validationError;
	
	@Autowired
	public InventoryController(InventoryRepository repository,
							   ServiceExecutor serviceExecutor,
							   SuppliersRepository supliersRepo){
		this.repository = repository;
		this.supliersRepo = supliersRepo;
		this.serviceExecutor = serviceExecutor;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Inventory>> listInventory(){
		List<Inventory> inventorys = new ArrayList<Inventory>();
		for (Inventory inventory: repository.findAll()){
			inventorys.add(inventory);
		}
		
		if (inventorys.isEmpty()){
			return new ResponseEntity<List<Inventory>>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Inventory>>(inventorys, HttpStatus.OK);
		}
	}
	
	/** update data inventory **/
	@PostMapping(value = "/update/{invId}/supliers/{supId}")
	public Inventory update(@RequestBody Inventory inventory,
							@PathVariable("invId") String invId,
							@PathVariable("supId") int supId){

		return supliersRepo.findById(supId)
				.map(suppliers -> {
					return repository.findById(invId)
							.map(currentData -> {
								currentData.setStock(inventory.getStock());
								currentData.setPrice(inventory.getPrice());
								currentData.setSuppliers(suppliers);
								return repository.save(currentData);
							}).orElseThrow(()
									-> new ResourceNotFoundException("invId : "+invId+" tidak ada"));
				}).orElseThrow(()
						-> new ResourceNotFoundException("supId : "+supId+" tidak ada"));
	}

	/** update data inventory by command **/
	@PostMapping(value = "/update-command/{invId}/suppliers/{supId}")
	public Inventory updateByCommand(@RequestBody @Valid UpdateInventoryRequest request,
									 @PathVariable("invId") String invId,
									 @PathVariable("supId") Integer supId){

		UpdateInventoryRequest requestInventory =
				UpdateInventoryRequest.builder()
				.inventoryId(invId)
				.suppliersId(supId)
				.price(request.getPrice())
				.stock(request.getStock())
				.build();

		return serviceExecutor
				.execute(UpdateInventoryCommand.class, requestInventory);
	}


	@PostMapping(value = "/create/{suppliersId}")
	public Inventory newInventory(@RequestBody @Valid AddNewInventoryRequest request,
											   @PathVariable("suppliersId") Integer suppliersId,
											   BindingResult bindingResult){

		AddNewInventoryRequest requestInventory = AddNewInventoryRequest
				.builder()
				.inventoryId(request.getInventoryId())
				.price(request.getPrice())
				.stock(request.getStock())
				.suppliersId(suppliersId)
				.build();

		return serviceExecutor.execute(AddNewInventoryCommand.class,
				requestInventory);
	}
}
