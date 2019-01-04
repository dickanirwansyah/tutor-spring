package com.dicka.demojpainventory.commandpattern;

import com.dicka.demojpainventory.command.Command;
import com.dicka.demojpainventory.entity.Inventory;
import com.dicka.demojpainventory.request.UpdateInventoryRequest;

public interface UpdateInventoryCommand extends Command<Inventory, UpdateInventoryRequest>{

}
