package com.project.dtos;

import lombok.Data;

@Data
public class AddShipmentDto {
    private String shipmentUnit;
    private String description;
    private Long shipmentCost;
    private String status;
}
