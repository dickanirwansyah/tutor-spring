package com.dicka.demojpainventory.commandpattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dicka.demojpainventory.command.AbstractCommand;
import com.dicka.demojpainventory.entity.Suppliers;
import com.dicka.demojpainventory.repository.SuppliersRepository;
import com.dicka.demojpainventory.request.AddNewSuppliersRequest;

/**
 * @role Java Web Developer
 * @author DickaNirwansyah
 * @github dickanirwansyah
 * @email dickanirwansyah@gmail.com
 * 
 */

@Component
public class AddNewSuppliersCommandImpl extends AbstractCommand<Suppliers, AddNewSuppliersRequest>
	implements AddNewSuppliersCommand{

	private final SuppliersRepository repository;
	
	@Autowired
	public AddNewSuppliersCommandImpl(SuppliersRepository repository){
		this.repository = repository;
	}
	
	@Override
	public Suppliers doExecute(AddNewSuppliersRequest request) {
		Suppliers suppliers = addNewSuppliers(request);
		/** execute save data suppliers **/
		return repository.save(suppliers);
	}
	
	/** suppliers builder **/
	private Suppliers addNewSuppliers(AddNewSuppliersRequest request){
		return Suppliers.builder()
				.name(request.getName())
				.phone(request.getPhone())
				.address(request.getPhone())
				.email(request.getEmail())
				.build();
	}
}
