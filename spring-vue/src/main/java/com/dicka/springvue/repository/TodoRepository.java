package com.dicka.springvue.repository;

import com.dicka.springvue.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByTitle(String title);
    Todo findTodoById(Long id);
}
