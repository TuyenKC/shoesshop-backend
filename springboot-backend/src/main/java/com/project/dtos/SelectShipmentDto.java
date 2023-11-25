package com.project.dtos;

import lombok.Data;

@Data
public class SelectShipmentDto {
    private String id;
    private String shipmentUnit;
    private Long shipmentCost;
}
