package com.project.repositories;

import com.project.entities.ProductImages;
import com.project.entities.ProductImagesKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImagesRepository extends JpaRepository<ProductImages, ProductImagesKey> {
}
