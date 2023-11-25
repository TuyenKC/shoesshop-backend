package com.project.controllers;

import com.project.dtos.AddCartItemDto;
import com.project.dtos.CartItemDto;
import com.project.dtos.ResponseObject;
import com.project.dtos.UpdateCartItemDto;
import com.project.exceptions.AppException;
import com.project.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/cartitem")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @PostMapping("")
    public ResponseEntity<?> addCartItem(@RequestBody AddCartItemDto addCartItemDto){
        try{
            cartItemService.addCartItem(addCartItemDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm vật phẩm giỏ hàng thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCartItem(@PathVariable Long id, @RequestBody UpdateCartItemDto updateCartItemDto){
        try{
            cartItemService.updateCartItem(id, updateCartItemDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật vật phẩm giỏ hàng thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long id){
        try{
            cartItemService.deleteCartItem(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Xóa vật phẩm giỏ hàng thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}
