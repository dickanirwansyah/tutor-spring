package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.exception.ResourceNotFoundException;
import com.dicka.commandpattern.model.ItemRequest;
import com.dicka.commandpattern.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteItemByIdCommandImpl extends AbstractCommand<Item, ItemRequest>
    implements DeleteItemByIdCommand{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item doExecute(ItemRequest request) {
        deleteItem(request.getItemId());
        return new Item();
    }

    public void deleteItem(Long itemId){
        itemRepository.deleteById(itemId);
    }
}
