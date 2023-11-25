package com.project.dtos;

import lombok.Data;

@Data
public class SelectPromotionDto {
    private String id;
    private String promotionName;
    private String promotionType;
    private Long discountValue;
}
