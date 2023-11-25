package com.project.controllers;

import com.project.dtos.*;
import com.project.exceptions.AppException;
import com.project.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @PreAuthorize("hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("")
    public ResponseEntity<?> getAllOrders(){
        try{
            List<OrdersDto> ordersDtoList = ordersService.getAllOrders();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách đơn hàng thành công", ordersDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrdersById(@PathVariable String id){
        try{
            OrdersDto ordersDto = ordersService.getOrdersById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy đơn hàng thành công",ordersDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/orderhistory/{username}")
    public ResponseEntity<?> getOrdersByUserName(@PathVariable String username){
        try{
            List<OrdersDto> ordersDto = ordersService.getAllOrdersByUsername(username);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy đơn hàng theo tên người dùng thành công",ordersDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @PostMapping("")
    public ResponseEntity<?> makeOrders(@RequestBody AddOrdersDto addOrdersDto, @RequestParam String action){
        try{
            ordersService.addOrders(addOrdersDto,action);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm đơn hàng thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrders(@PathVariable String id,@RequestBody UpdateOrdersDto updateOrdersDto){
        try{
            ordersService.updateOrders(id, updateOrdersDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật đơn hàng thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrders(@PathVariable String id) {
        try {
            ordersService.deleteOrders(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa đơn hàng thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/revenueproduct/{productName}")
    public ResponseEntity<?> getRevenueByProduct(@PathVariable String productName){
        try{
            RevenueDto revenueDateDtoList = ordersService.getRevenueByProduct(productName);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy doanh thu theo sản phẩm thành công", revenueDateDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/revenuebrand/{brandName}")
    public ResponseEntity<?> getRevenueByBrand(@PathVariable String brandName){
        try{
            RevenueDto revenueDateDtoList = ordersService.getRevenueByBrand(brandName);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy doanh thu theo sản phẩm thành công", revenueDateDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/revenuedate")
    public ResponseEntity<?> getRevenueByDate(@RequestParam String startDate, @RequestParam String endDate){
        try{
            RevenueDto revenueDateDtoList = ordersService.getRevenueByDate(startDate, endDate);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy doanh thu theo sản phẩm thành công", revenueDateDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}