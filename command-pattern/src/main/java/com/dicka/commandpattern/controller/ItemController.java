package com.dicka.commandpattern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dicka.commandpattern.commandPattern.CreateNewItemCommand;
import com.dicka.commandpattern.commandPattern.ServiceExecutor;
import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.model.ItemRequest;

@RestController
@RequestMapping(value = "/api/item")
public class ItemController {

	@Autowired
	private ServiceExecutor serviceExecutor;
	
	@PostMapping(value = "/create")
	public Item createItem(@RequestBody ItemRequest request){
		ItemRequest itemRequest = ItemRequest
				.builder()
				.itemName(request.getItemName())
				.itemPrice(request.getItemPrice())
				.build();
		
		return serviceExecutor
				.execute(CreateNewItemCommand.class, request);
	}
 	
}
