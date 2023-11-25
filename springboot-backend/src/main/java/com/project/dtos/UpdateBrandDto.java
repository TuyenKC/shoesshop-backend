package com.project.dtos;

import lombok.Data;

@Data
public class UpdateBrandDto {
    private String brandName;
    private String description;
    private String status;
}
