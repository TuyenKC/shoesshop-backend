package com.project.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SelectProductDto {
    private String id;
    private String productName;
    private Long salePrice;
    private List<ItemDto> itemList;
}
