package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.model.ItemRequest;
import com.dicka.commandpattern.repository.ItemRepository;

@Component
public class UpdateItemByIdCommandImpl extends AbstractCommand<Item, ItemRequest>
	implements UpdateItemByIdCommand{

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Item doExecute(ItemRequest request) {
		return itemRepository.findById(request.getItemId())
				.map(currentData -> {
					currentData.setName(request.getItemName());
					currentData.setPrice(request.getItemPrice());
					return itemRepository.save(currentData);
				}).orElseThrow(() -> new ResourceNotFoundException("" +
						"maaf id item : "+request.getItemId()+ " tidak ada"));
	}
}
