package com.dicka.demojpainventory.commandpattern;

import com.dicka.demojpainventory.command.Command;
import com.dicka.demojpainventory.entity.Inventory;
import com.dicka.demojpainventory.request.AddNewInventoryRequest;

public interface AddNewInventoryCommand extends Command<Inventory, AddNewInventoryRequest> {

}
