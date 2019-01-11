package com.dicka.commandpattern.repository.sample;

import com.dicka.commandpattern.entity.sample.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
