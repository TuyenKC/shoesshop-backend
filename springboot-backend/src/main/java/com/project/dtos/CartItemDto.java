package com.project.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CartItemDto {
    private Long id;
    private String productId;
    private String productName;
    private String size;
    private String cartId;
    private List<String> urlImagesList;
    private int quantity;
    private int quantityInStock;
    private long price;
    private long salePrice;
}
