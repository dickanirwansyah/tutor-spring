package com.dicka.springvue.controller;

import com.dicka.springvue.entity.Todo;
import com.dicka.springvue.exception.ResourceNotFound;
import com.dicka.springvue.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/todo")
//@CrossOrigin(origins = {"*"})
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
    private Map<String, String> validation;

    @GetMapping
    public ResponseEntity<List<Todo>> listTodo(){
        List<Todo> todos = new ArrayList<>();
        for (Todo todo : todoRepository.findAll()){
            todos.add(todo);
        }

        if (todos.isEmpty()){
            return new ResponseEntity<List<Todo>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
    }

    @PatchMapping(value = "/{todoId}")
    public ResponseEntity<Todo> updateTodo(@RequestBody @Valid Todo todo,
                                             @PathVariable("todoId") Long todoId){

        return todoRepository.findById(todoId)
                .map(currentTodo -> {
                    currentTodo.setTitle(todo.getTitle());
                    return ResponseEntity.ok(
                            todoRepository.save(currentTodo)
                    );
                }).orElseThrow(() ->
                        new ResourceNotFound("todo {} "+todoId+" not found."));
    }

    @DeleteMapping(value = "/{todoId}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable("todoId")Long todoId){

        Todo todo = todoRepository.findTodoById(todoId);
        if (todo == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        todoRepository.delete(todo);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createTodo(@RequestBody @Valid Todo todo,
                                           BindingResult bindingResult){

        List<Todo> findName = todoRepository.findByTitle(todo.getTitle());
        if (findName.size() > 0){
            return new ResponseEntity<Object>(HttpStatus.CONFLICT);
        }

        if (bindingResult.hasErrors()){
            validation = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                validation.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Object>(validation, HttpStatus.NOT_ACCEPTABLE);
        }

        Todo td = Todo
                .builder()
                .title(todo.getTitle())
                .build();
         todoRepository.save(td);
         return new ResponseEntity<Object>(td, HttpStatus.CREATED);
    }
}
