package com.project.controllers;

import com.project.dtos.*;
import com.project.exceptions.AppException;
import com.project.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/shipment")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/valid")
    public ResponseEntity<?> getAllShipmentValid(){
        try{
            List<SelectShipmentDto> selectShipmentDtos = shipmentService.getAllShipmentValid();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách đơn vị vận chuyển hợp lệ thành công", selectShipmentDtos));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("")
    public ResponseEntity<?> getAllShipment(){
        try{
            List<ShipmentDto> shipmentDtoList = shipmentService.getAllShipment();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách đơn vị vận chuyển thành công", shipmentDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getShipmentById(@PathVariable String id){
        try{
            ShipmentDto shipmentDto = shipmentService.getShipmentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy thông tin đơn vị vận chuyển thành công",shipmentDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @PostMapping("")
    public ResponseEntity<?> addShipment(@RequestBody AddShipmentDto addShipmentDto){
        try{
            shipmentService.addShipment(addShipmentDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm đơn vị vận chuyển thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }

    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateShipment(@PathVariable String id,@RequestBody UpdateShipmentDto updateShipmentDto){
        try{
            shipmentService.updateShipment(id, updateShipmentDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật đơn vị vận chuyển thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShipment(@PathVariable String id) {
        try {
            shipmentService.deleteShipment(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa đơn vị vận chuyển thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}