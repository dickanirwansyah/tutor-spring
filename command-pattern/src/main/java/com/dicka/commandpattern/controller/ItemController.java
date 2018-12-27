package com.dicka.commandpattern.controller;

import com.dicka.commandpattern.commandPattern.*;
import com.dicka.commandpattern.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.model.ItemRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/item")
@CrossOrigin(origins = {"*"})
public class ItemController {


	private final ServiceExecutor serviceExecutor;
	private final ItemRepository itemRepository;

	@Autowired
	public ItemController(ServiceExecutor serviceExecutor,
						  ItemRepository itemRepository){

		this.serviceExecutor = serviceExecutor;
		this.itemRepository = itemRepository;
	}

	/** delete by item **/
	@DeleteMapping(value = "/delete/{itemId}")
	public Item deleteById(@PathVariable Long itemId){
		ItemRequest itemRequest = ItemRequest
				.builder()
				.id(itemId)
				.build();

		return serviceExecutor
				.execute(DeleteItemByIdCommand.class, itemRequest);
	}

	/** find item byId **/
	@GetMapping(value = "/find")
	public Item findById(@RequestParam("itemId") Long itemId){
		ItemRequest itemRequest = ItemRequest
				.builder()
				.id(itemId)
				.build();

		return serviceExecutor
				.execute(FindItemIdCommand.class, itemRequest);
	}


	/** create new Item **/
	
	@PostMapping(value = "/create")
	public Item createItem(@RequestBody ItemRequest request){
		ItemRequest itemRequest = ItemRequest
				.builder()
				.name(request.getName())
				.price(request.getPrice())
				.build();
		
		return serviceExecutor
				.execute(CreateNewItemCommand.class, request);
	}

	/** list item **/
	@GetMapping(value = "/list")
	public List<Item> getListItem(){
		List<Item> items = new ArrayList<>();
		for (Item item : itemRepository.findAll()){
			items.add(item);
		}
		return items;
	}

	/** update by path variable **/
	@PostMapping(value = "/update/{itemId}")
	public Item updateById(@PathVariable("itemId") Long itemId,
						   @RequestBody ItemRequest request){
		ItemRequest itemRequest = ItemRequest
				.builder()
				.id(itemId)
				.name(request.getName())
				.price(request.getPrice())
				.build();

		return serviceExecutor
				.execute(UpdateItemCommand.class, itemRequest);
	}

	/** update by request body **/
	@PostMapping(value = "/update")
	public Item updateItem(@RequestBody ItemRequest request){
		ItemRequest itemRequest = ItemRequest
				.builder()
				.name(request.getName())
				.price(request.getPrice())
				.build();
		
		return serviceExecutor
				.execute(UpdateItemByIdCommand.class, request);
	}
}
