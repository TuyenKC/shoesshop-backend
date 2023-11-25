package com.project.repositories;

import com.project.entities.CategoryProduct;
import com.project.entities.CategoryProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRepository extends JpaRepository<CategoryProduct, CategoryProductKey> {
}
