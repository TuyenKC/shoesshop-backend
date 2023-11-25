package com.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddProductImagesDto {
    private String productId;
    private String imagesId;
}
