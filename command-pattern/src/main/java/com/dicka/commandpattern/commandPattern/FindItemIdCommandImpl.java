package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.exception.ResourceNotFoundException;
import com.dicka.commandpattern.model.ItemRequest;
import com.dicka.commandpattern.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindItemIdCommandImpl extends AbstractCommand<Item, ItemRequest>
implements FindItemIdCommand{

    private final ItemRepository itemRepository;

    @Autowired
    public FindItemIdCommandImpl(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public Item doExecute(ItemRequest request) {
        return itemRepository.findById(request.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("sorry item : "+
                        request.getId()+" not found."));
    }
}
