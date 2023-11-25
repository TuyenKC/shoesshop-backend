package com.project.dtos;

import lombok.Data;

import java.util.List;
@Data
public class UpdateProductDto {
    private String productName;
    private String description;
    private Long price;
    private Long salePrice;
    private String status;
    private String brandId;
    private List<String> categoryListName;
    private List<AddItemDto> itemList;
    private List<String> imagesIdList;
    private Long totalSold;
    private Long totalView;
}
