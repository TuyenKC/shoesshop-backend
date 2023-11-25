package com.project.repositories;

import com.project.entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Images,String> {
}
