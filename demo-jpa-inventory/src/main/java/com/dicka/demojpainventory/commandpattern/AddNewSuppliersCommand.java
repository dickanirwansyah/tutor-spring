package com.dicka.demojpainventory.commandpattern;

import com.dicka.demojpainventory.command.Command;
import com.dicka.demojpainventory.entity.Suppliers;
import com.dicka.demojpainventory.request.AddNewSuppliersRequest;

public interface AddNewSuppliersCommand extends Command<Suppliers, AddNewSuppliersRequest>{
}
