package com.project.controllers;

import com.project.dtos.AddBrandDto;
import com.project.dtos.BrandDto;
import com.project.dtos.ResponseObject;
import com.project.dtos.UpdateBrandDto;
import com.project.entities.Brand;
import com.project.exceptions.AppException;
import com.project.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<?> getAllBrand(){
        try{
            List<BrandDto> brandDtoList = brandService.getAllBrand();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách nhãn hiệu thành công", brandDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/valid")
    public ResponseEntity<?> getAllBrandValid(){
        try{
            List<BrandDto> brandDtoList = brandService.getAllBrandValid();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách nhãn hiệu hợp lệ thành công", brandDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable String id){
        try{
            BrandDto brandDto = brandService.getBrandById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy nhãn hiệu thành công",brandDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @PostMapping("")
    public ResponseEntity<?> addBrand(@RequestBody AddBrandDto addBrandDto){
        try{
            brandService.addBrand(addBrandDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm nhãn hiệu thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable String id,@RequestBody UpdateBrandDto updateBrandDto){
        try{
            brandService.updateBrand(id, updateBrandDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật nhãn hiệu thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable String id) {
        try {
            brandService.deleteBrand(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa nhãn hiệu thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}
