package com.project.repositories;

import com.project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,String> {
    public Optional<Product> findByProductName(String productName);
    boolean existsByProductName(String productName);

}
