package com.project.controllers;

import com.project.dtos.CartDto;
import com.project.dtos.ResponseObject;
import com.project.exceptions.AppException;
import com.project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable String id){
        try{
            CartDto cartDto = cartService.getCartById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy giỏ hàng thành công",cartDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/users/{username}")
    public ResponseEntity<?> getCartByUsername(@PathVariable String username){
        try{
            CartDto cartDto = cartService.getCartByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy giỏ hàng người dùng thành công",cartDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}
