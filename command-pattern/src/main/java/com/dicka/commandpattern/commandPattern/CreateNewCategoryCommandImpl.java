package com.dicka.commandpattern.commandPattern;

import com.dicka.commandpattern.entity.sample.Category;
import com.dicka.commandpattern.model.CategoryRequest;
import com.dicka.commandpattern.repository.sample.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateNewCategoryCommandImpl extends AbstractCommand<Category, CategoryRequest>
implements CreateNewCategoryCommand{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CreateNewCategoryCommandImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category doExecute(CategoryRequest request) {
        Category category = newCategory(request);
        return categoryRepository.save(category);
    }

    private Category newCategory(CategoryRequest request){
        return Category.builder()
                .name(request.getName())
                .build();
    }
}
