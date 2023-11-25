package com.project.repositories;

import com.project.entities.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate,String> {
}
