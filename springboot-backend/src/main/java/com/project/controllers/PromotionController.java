package com.project.controllers;

import com.project.dtos.*;
import com.project.exceptions.AppException;
import com.project.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("")
    public ResponseEntity<?> getAllPromotion(){
        try{
            List<PromotionDto> promotionDtoList = promotionService.getAllPromotion();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách khuyến mại thành công", promotionDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/valid")
    public ResponseEntity<?> getAllPromotionValid(){
        try{
            List<SelectPromotionDto> promotionDtoList = promotionService.getAllPromotionValid();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách khuyến mại hợp lệ thành công", promotionDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPromotionById(@PathVariable String id){
        try{
            PromotionDto promotionDto = promotionService.getPromotionById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy thông tin khuyến mại thành công",promotionDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @PostMapping("")
    public ResponseEntity<?> addPromotion(@RequestBody AddPromotionDto addPromotionDto){
        try{
            promotionService.addPromotion(addPromotionDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm khuyến mại thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePromotion(@PathVariable String id,@RequestBody UpdatePromotionDto updatePromotionDto){
        try{
            promotionService.updatePromotion(id, updatePromotionDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật khuyến mại thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable String id) {
        try {
            promotionService.deletePromotion(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa khuyến mại thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}