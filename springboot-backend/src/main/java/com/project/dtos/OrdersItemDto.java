package com.project.dtos;

import lombok.Data;

import java.util.List;
@Data
public class OrdersItemDto {
    private Long id;
    private String productId;
    private String productName;
    private String size;
    private String cartId;
    private String ordersId;
    private List<String> urlImagesList;
    private String statusRate;
    private int quantity;
    private int quantityInStock;
    private long price;
    private long salePrice;
}
