package com.project.dtos;

import lombok.Data;

import javax.persistence.Column;
@Data
public class ShipmentDto {
    private String id;
    private String shipmentUnit;
    private String description;
    private Long shipmentCost;
    private String status;
}
