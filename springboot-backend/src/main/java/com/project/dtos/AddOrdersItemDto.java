package com.project.dtos;

import lombok.Data;

import java.util.List;

@Data
public class AddOrdersItemDto {
    private Long id;
    private String productId;
    private String size;
    private String cartId;
    private String ordersId;
    private String statusRate;
    private int quantity;
    private int quantityInStock;
}
