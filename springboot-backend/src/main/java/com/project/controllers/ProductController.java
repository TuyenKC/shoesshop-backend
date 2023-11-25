package com.project.controllers;

import com.project.dtos.AddProductDto;
import com.project.dtos.ResponseObject;
import com.project.dtos.ProductDto;
import com.project.dtos.UpdateProductDto;
import com.project.exceptions.AppException;
import com.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<?> getAllProduct(){
        try{
            List<ProductDto> productDtoList = productService.getAllProduct();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách sản phẩm thành công", productDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/valid")
    public ResponseEntity<?> getAllProductValid(){
        try{
            List<ProductDto> productDtoList = productService.getAllProductValid();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách sản phẩm hợp lệ thành công", productDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/mostview")
    public ResponseEntity<?> getMostViewProductList(){
        try{
            List<ProductDto> productDtoList = productService.getMostViewProductList();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách sản phẩm theo lượt xem thành công", productDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/mostsold")
    public ResponseEntity<?> getMostSoldProductList(){
        try{
            List<ProductDto> productDtoList = productService.getMostSoldProductList();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách sản phẩm theo lượt bán sản phẩm thành công", productDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/mostrate")
    public ResponseEntity<?> getMostRateProductList(){
        try{
            List<ProductDto> productDtoList = productService.getMostRateProductList();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách sản phẩm theo lượt đánh giá sản phẩm thành công", productDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id){
        try{
            ProductDto productDto = productService.getProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy thông tin sản phẩm thành công",productDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestBody AddProductDto addProductDto){
        try{
            productService.addProduct(addProductDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm sản phẩm thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id,@RequestBody UpdateProductDto updateProductDto){
        try{
            productService.updateProduct(id, updateProductDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật sản phẩm thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa sản phẩm thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}
