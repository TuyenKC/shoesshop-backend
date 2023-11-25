package com.project.repositories;

import com.project.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,String> {
    Optional<Category> findByCategoryName(String name);
    boolean existsByCategoryName(String categoryName);

}
