package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.model.ItemRequest;

public interface UpdateItemByIdCommand extends Command<Item, ItemRequest>{

}
