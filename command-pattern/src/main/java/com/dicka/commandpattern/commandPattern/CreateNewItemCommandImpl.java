package com.dicka.commandpattern.commandPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.model.ItemRequest;
import com.dicka.commandpattern.repository.ItemRepository;

@Component
public class CreateNewItemCommandImpl extends AbstractCommand<Item, ItemRequest>
	implements CreateNewItemCommand{

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Item doExecute(ItemRequest request) {
		Item item = newItem(request.getName(), request.getPrice());
		return itemRepository.save(item);
	}

	private Item newItem(String name, double price){
		return Item.builder()
				.name(name)
				.price(price)
				.build();
	}
}
