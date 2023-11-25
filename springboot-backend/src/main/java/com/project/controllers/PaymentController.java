package com.project.controllers;

import com.project.dtos.*;
import com.project.exceptions.AppException;
import com.project.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("")
    public ResponseEntity<?> getAllPayment(){
        try{
            List<PaymentDto> paymentDtoList = paymentService.getAllPayment();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách phương thức thanh toán thành công", paymentDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/valid")
    public ResponseEntity<?> getAllPaymentValid(){
        try{
            List<SelectPaymentDto> selectPaymentDtos = paymentService.getAllPaymentValid();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách phương thức thanh toán hợp lệ thành công", selectPaymentDtos));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR_SALES') or hasRole('ROLE_STORE_OWNER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPaymentById(@PathVariable String id){
        try{
            PaymentDto paymentDto = paymentService.getPaymentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy thông tin phương thức thanh toán thành công",paymentDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @PostMapping("")
    public ResponseEntity<?> addPayment(@RequestBody AddPaymentDto addPaymentDto){
        try{
            paymentService.addPayment(addPaymentDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm phương thức thanh toán thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePayment(@PathVariable String id,@RequestBody UpdatePaymentDto updatePaymentDto){
        try{
            paymentService.updatePayment(id, updatePaymentDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật phương thức thanh toán thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_STORE_OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePayment(@PathVariable String id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa phương thức thanh toán thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}
