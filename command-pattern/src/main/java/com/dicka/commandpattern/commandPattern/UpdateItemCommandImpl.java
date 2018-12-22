package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.exception.ResourceNotFoundException;
import com.dicka.commandpattern.model.ItemRequest;
import com.dicka.commandpattern.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateItemCommandImpl extends AbstractCommand<Item, ItemRequest>
    implements UpdateItemCommand{

    private final ItemRepository itemRepository;

    @Autowired
    public UpdateItemCommandImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public Item doExecute(ItemRequest request) {
        Item item = findItem(request);
        if (item == null){
            throw new ResourceNotFoundException("" +
                    "maaf item : "+request.getItemId()+" tidak ditemukan.");
        }else {
            item.setName(request.getItemName());
            item.setPrice(request.getItemPrice());
            return itemRepository.save(item);
        }
    }

    private Item findItem(ItemRequest request){
        Item item = itemRepository.findItemById(request.getItemId());
        return item;
    }
}
