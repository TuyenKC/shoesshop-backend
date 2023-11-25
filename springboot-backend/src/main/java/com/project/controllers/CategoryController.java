package com.project.controllers;

import com.project.dtos.*;
import com.project.exceptions.AppException;
import com.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<?> getAllCategory(){
        try{
            List<CategoryDto> categoryDtoList = categoryService.getAllCategory();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách danh mục thành công", categoryDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/valid")
    public ResponseEntity<?> getAllCategoryValid(){
        try{
            List<CategoryDto> categoryDtoList = categoryService.getAllCategoryValid();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách danh mục hợp lệ thành công", categoryDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable String id){
        try{
            CategoryDto categoryDto = categoryService.getCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh mục thành công",categoryDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @PostMapping("")
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryDto addCategoryDto){
        try{
            categoryService.addCategory(addCategoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm danh mục thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id,@RequestBody UpdateCategoryDto updateCategoryDto){
        try{
            categoryService.updateCategory(id, updateCategoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật danh mục thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa danh mục thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}
