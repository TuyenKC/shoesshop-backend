package com.project.services;

import com.project.dtos.AddShipmentDto;
import com.project.dtos.SelectShipmentDto;
import com.project.dtos.ShipmentDto;
import com.project.dtos.UpdateShipmentDto;
import com.project.entities.Shipment;

import java.util.List;

public interface ShipmentService {
    public Shipment findById(String id);
    public ShipmentDto addShipment(AddShipmentDto addShipmentDto);
    public ShipmentDto updateShipment(String id, UpdateShipmentDto updateShipmentDto);
    public void deleteShipment(String id);
    public ShipmentDto getShipmentById(String id);
    public List<ShipmentDto> getAllShipment();
    public List<SelectShipmentDto> getAllShipmentValid();

}
