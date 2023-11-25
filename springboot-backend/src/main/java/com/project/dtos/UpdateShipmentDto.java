package com.project.dtos;

import lombok.Data;

@Data
public class UpdateShipmentDto {
    private String shipmentUnit;
    private String description;
    private Long shipmentCost;
    private String status;
}
