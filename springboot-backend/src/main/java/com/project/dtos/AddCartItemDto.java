package com.project.dtos;

import lombok.Data;

@Data
public class AddCartItemDto {
    private String productId;
    private String size;
    private String cartId;
    private int quantity;
    private int quantityInStock;
}
