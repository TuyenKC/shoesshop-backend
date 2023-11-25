package com.project.dtos;

import lombok.Data;

import java.util.List;
@Data
public class AddProductDto {
    private String productName;
    private String description;
    private Long price;
    private Long salePrice;
    private String status;
    private String brandId;
    private List<String> categoryListName;
    private List<AddItemDto> itemList;
    private List<String> imagesIdList;
}
