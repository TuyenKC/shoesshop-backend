package com.project.repositories;

import com.project.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment,String> {
    boolean existsByShipmentUnit(String shipmentUnit);
}
