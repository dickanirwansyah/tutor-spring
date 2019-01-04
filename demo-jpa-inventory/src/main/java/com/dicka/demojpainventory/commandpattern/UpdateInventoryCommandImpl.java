package com.dicka.demojpainventory.commandpattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dicka.demojpainventory.command.AbstractCommand;
import com.dicka.demojpainventory.entity.Inventory;
import com.dicka.demojpainventory.exception.ResourceNotFoundException;
import com.dicka.demojpainventory.repository.InventoryRepository;
import com.dicka.demojpainventory.repository.SuppliersRepository;
import com.dicka.demojpainventory.request.UpdateInventoryRequest;

@Component
public class UpdateInventoryCommandImpl extends AbstractCommand<Inventory, UpdateInventoryRequest>
implements UpdateInventoryCommand{

	private final InventoryRepository repoInventory;
	private final SuppliersRepository repoSuppliers;
	
	@Autowired
	public UpdateInventoryCommandImpl(InventoryRepository repoInventory,
			SuppliersRepository repoSuppliers) {
		this.repoInventory = repoInventory;
		this.repoSuppliers = repoSuppliers;
	}
	
	@Override
	public Inventory doExecute(UpdateInventoryRequest request) {
		return update(request);
	}

	private Inventory update(UpdateInventoryRequest request){
		return repoSuppliers.findById(request.getSuppliersId())
				.map(suppliers -> {
					return repoInventory.findById(request.getInventoryId())
							.map(currentData -> {
								currentData.setPrice(request.getPrice());
								currentData.setStock(request.getStock());
								currentData.setSuppliers(suppliers);
								return repoInventory.save(currentData);
							})
							.orElseThrow(() ->
							new ResourceNotFoundException(""));
				})
				.orElseThrow(() ->
				new ResourceNotFoundException(""));
	}
}
