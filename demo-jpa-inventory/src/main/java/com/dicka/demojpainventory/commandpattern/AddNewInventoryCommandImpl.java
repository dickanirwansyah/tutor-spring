package com.dicka.demojpainventory.commandpattern;

import com.dicka.demojpainventory.command.AbstractCommand;
import com.dicka.demojpainventory.entity.Inventory;
import com.dicka.demojpainventory.exception.ResourceNotFoundException;
import com.dicka.demojpainventory.repository.InventoryRepository;
import com.dicka.demojpainventory.repository.SuppliersRepository;
import com.dicka.demojpainventory.request.AddNewInventoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddNewInventoryCommandImpl extends AbstractCommand<Inventory, AddNewInventoryRequest>
implements AddNewInventoryCommand{

    private final InventoryRepository inventoryRepository;
    private final SuppliersRepository suppliersRepository;

    @Autowired
    public AddNewInventoryCommandImpl(InventoryRepository inventoryRepository,
                                      SuppliersRepository suppliersRepository){
        this.inventoryRepository = inventoryRepository;
        this.suppliersRepository = suppliersRepository;
    }


    @Override
    public Inventory doExecute(AddNewInventoryRequest request) {
        return newInventory(request);
    }

    private Inventory newInventory(AddNewInventoryRequest request){

        return suppliersRepository.findById(request.getSuppliersId())
                .map(suppliers -> {

                    Inventory inventory = Inventory
                            .builder()
                            .inventoryId(request.getInventoryId())
                            .stock(request.getStock())
                            .price(request.getPrice())
                            .suppliers(suppliers)
                            .build();

                    return inventoryRepository.save(inventory);

                }).orElseThrow(() -> new ResourceNotFoundException("maaf id : "
                +request.getSuppliersId()+" tidak ada"));
    }
}
