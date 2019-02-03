package com.dicka.commandpattern.controller.sample;

import com.dicka.commandpattern.commandPattern.CreateNewCategoryCommand;
import com.dicka.commandpattern.commandPattern.ServiceExecutor;
import com.dicka.commandpattern.entity.sample.Category;
import com.dicka.commandpattern.model.CategoryRequest;
import com.dicka.commandpattern.repository.sample.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/category")
@CrossOrigin(origins = {"*"})
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ServiceExecutor serviceExecutor;

    @GetMapping
    public ResponseEntity<List<Category>> listCategory(){
        List<Category> categories = new ArrayList<>();

        for (Category category : categoryRepository.findAll()){
            categories.add(category);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Category category){
        Category newCategory = Category
                .builder()
                .name(category.getName())
                .build();

        categoryRepository.save(newCategory);
        return new ResponseEntity<Object>(newCategory,
                HttpStatus.CREATED);
    }

    /** using controller command pattern **/
    @PostMapping(value = "/create")
    public Category newCategory(@RequestBody CategoryRequest request){
        CategoryRequest categoryRequest = CategoryRequest
                .builder()
                .name(request.getName())
                .build();

        return serviceExecutor.execute(CreateNewCategoryCommand.class,
                categoryRequest);
    }

}
