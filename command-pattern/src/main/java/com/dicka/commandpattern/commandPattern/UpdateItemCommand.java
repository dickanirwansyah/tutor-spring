package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.model.ItemRequest;

public interface UpdateItemCommand extends Command<Item, ItemRequest> {
}
