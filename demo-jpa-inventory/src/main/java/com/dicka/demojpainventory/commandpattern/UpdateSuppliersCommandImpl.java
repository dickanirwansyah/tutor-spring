package com.dicka.demojpainventory.commandpattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dicka.demojpainventory.command.AbstractCommand;
import com.dicka.demojpainventory.entity.Suppliers;
import com.dicka.demojpainventory.exception.ResourceNotFoundException;
import com.dicka.demojpainventory.repository.SuppliersRepository;
import com.dicka.demojpainventory.request.UpdateSuppliersRequest;

@Component
public class UpdateSuppliersCommandImpl extends AbstractCommand<Suppliers, UpdateSuppliersRequest>
implements UpdateSupplierCommand{

	private final SuppliersRepository repository;
	
	@Autowired
	public UpdateSuppliersCommandImpl(SuppliersRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Suppliers doExecute(UpdateSuppliersRequest request) {
		return repository.findById(request.getId())
				.map(currentData -> {
					currentData.setEmail(request.getEmail());
					currentData.setName(request.getName());
					currentData.setPhone(request.getPhone());
					currentData.setAddress(request.getAddress());
					return repository.save(currentData);
				}).orElseThrow(() -> new ResourceNotFoundException(" id : "+request.getId()
						+" tidak ada."));
	}
}
