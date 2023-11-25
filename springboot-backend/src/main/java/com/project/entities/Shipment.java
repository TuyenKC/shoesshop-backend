package com.project.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Shipment {
    @Id
    private String id;
    @Column(name = "shipment_unit")
    private String shipmentUnit;
    private String description;
    @Column(name = "shipment_cost")
    private Long shipmentCost;
    private String status;
}
