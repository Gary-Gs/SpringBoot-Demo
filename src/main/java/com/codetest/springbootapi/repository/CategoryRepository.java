package com.codetest.springbootapi.repository;

import com.codetest.springbootapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByCategoryNameIn(List<String> name);
}
