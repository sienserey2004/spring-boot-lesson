package com.setec.appcoffeeshop.features.categories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findCategoryById(int id);
    Page<Category> findByNameContainingIgnoreCase(String keyword, Pageable pageable);

}
