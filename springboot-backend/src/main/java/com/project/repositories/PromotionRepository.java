package com.project.repositories;

import com.project.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, String> {
    boolean existsByPromotionName(String promotionName);
    boolean existsByDiscountCode(String discountCode);

}
