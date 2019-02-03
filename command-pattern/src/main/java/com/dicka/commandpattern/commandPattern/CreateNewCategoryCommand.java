package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.sample.Category;
import com.dicka.commandpattern.model.CategoryRequest;

public interface CreateNewCategoryCommand extends Command<Category, CategoryRequest> {
}
