package com.project.services.impl;

import com.project.dtos.*;
import com.project.dtos.ShipmentDto;
import com.project.entities.Shipment;
import com.project.entities.Shipment;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.repositories.ShipmentRepository;
import com.project.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public Shipment findById(String id) {
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        if(!shipment.isPresent()){
            throw new AppException("Đơn vị vận chuyển không tồn tại","404");
        }
        return shipment.get();
    }

    @Override
    public ShipmentDto addShipment(AddShipmentDto addShipmentDto) {
        if(shipmentRepository.existsByShipmentUnit(addShipmentDto.getShipmentUnit())){
            throw new AppException("Tên đơn vị vận chuyển đã tồn tại","409");
        }
        Shipment shipment = new DtoToEntityMapper().dtoToEntityAddShipment(addShipmentDto);
        Shipment savedShipment = shipmentRepository.save(shipment);
        return new EntityToDtoMapper().entityToDtoShipment(savedShipment);
    }

    @Override
    public ShipmentDto updateShipment(String id, UpdateShipmentDto updateShipmentDto) {
        if(shipmentRepository.findById(id) == null)
            throw new AppException("Đơn vị vận chuyển không tồn tại", "404");
        Shipment shipment = shipmentRepository.findById(id).get();
        if(!updateShipmentDto.getShipmentUnit().equals(shipment.getShipmentUnit()) && shipmentRepository.existsByShipmentUnit(updateShipmentDto.getShipmentUnit())){
            throw new AppException("Tên đơn vị vận chuyển đã tồn tại","409");
        }
        shipment.setDescription(updateShipmentDto.getDescription());
        shipment.setStatus(updateShipmentDto.getStatus());
        shipment.setShipmentCost(updateShipmentDto.getShipmentCost());
        shipment.setShipmentUnit(updateShipmentDto.getShipmentUnit());
        Shipment savedShipment = shipmentRepository.save(shipment);
        return new EntityToDtoMapper().entityToDtoShipment(savedShipment);
    }

    @Override
    public void deleteShipment(String id) {
        if(shipmentRepository.findById(id) == null)
            throw new AppException("Đơn vị vận chuyển không tồn tại", "404");
        shipmentRepository.deleteById(id);
    }

    @Override
    public ShipmentDto getShipmentById(String id) {
        if(!shipmentRepository.findById(id).isPresent())
            throw new AppException("Đơn vị vận chuyển không tồn tại", "404");
        Shipment shipment = shipmentRepository.findById(id).get();
        return new EntityToDtoMapper().entityToDtoShipment(shipment);
    }

    @Override
    public List<ShipmentDto> getAllShipment() {
        List<ShipmentDto> shipmentDtos = shipmentRepository.findAll().stream()
                .map(shipment -> new EntityToDtoMapper().entityToDtoShipment(shipment))
                .collect(Collectors.toList());
        return shipmentDtos;
    }

    @Override
    public List<SelectShipmentDto> getAllShipmentValid() {
        List<SelectShipmentDto> shipmentDtos = shipmentRepository.findAll().stream()
                .filter(shipment -> "Active".equals(shipment.getStatus()))
                .map(shipment -> new EntityToDtoMapper().entityToDtoSelectShipment(shipment))
                .collect(Collectors.toList());
        return shipmentDtos;
    }
}
