package com.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateItemDto {
    private String productId;
    private String size;
    private int quantityInStock;
}
