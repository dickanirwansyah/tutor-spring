package com.dicka.commandpattern.notcommand.service;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.exception.ResourceNotFoundException;
import com.dicka.commandpattern.model.ItemRequest;
import com.dicka.commandpattern.notcommand.request.ItemRequestNotCommand;
import com.dicka.commandpattern.notcommand.response.ItemResponse;
import com.dicka.commandpattern.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    /** list all item **/
    public List<Item> listItem(){
        List<Item> items = new ArrayList<>();
        for (Item item : itemRepository.findAll()){
            items.add(item);
        }
        return items;
    }

    /** list item by price **/
    public List<Item> listItemByPrice(double price){
        List<Item> items = new ArrayList<>();
        for (Item item : itemRepository.findItemByPrice(price)){
            items.add(item);
        }
        return items;
    }

    /** create item **/
    public ItemResponse createItem(ItemRequestNotCommand request){

        Item item = Item
                .builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();

        itemRepository.save(item);

        return new ItemResponse("success", item);
    }

    /** update item **/
    public ItemResponse updateItem(Long itemId, ItemRequestNotCommand request){

        Item item =  itemRepository.findById(itemId)
                .map(currentData -> {
                    currentData.setName(request.getName());
                    currentData.setPrice(request.getPrice());
                    return itemRepository.save(currentData);
                }).orElseThrow(() -> new ResourceNotFoundException("item : "+itemId+" " +
                 "tidak ada"));

         return new ItemResponse("success", item);
    }
}
