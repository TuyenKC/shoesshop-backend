package com.project.dtos;

import lombok.Data;

@Data
public class AddRateDto {
    private String id;
    private int star;
    private String userId;
    private String productId;
    private Long ordersItemId;
}
