package com.dicka.commandpattern.commandPattern;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.dicka.commandpattern.entity.Item;
import com.dicka.commandpattern.model.ItemRequest;

public interface CreateNewItemCommand extends Command<Item, ItemRequest>{
	
}
