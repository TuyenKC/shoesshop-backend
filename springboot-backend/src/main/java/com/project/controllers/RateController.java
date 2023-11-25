package com.project.controllers;

import com.project.dtos.AddRateDto;
import com.project.dtos.ResponseObject;
import com.project.dtos.RateDto;
import com.project.exceptions.AppException;
import com.project.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/rate")
public class RateController {
    @Autowired
    private RateService rateService;

    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<?> getAllRate(){
        try{
            List<RateDto> rateDtoList = rateService.getAllRate();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách đánh giá thành công", rateDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getRateById(@PathVariable String id){
        try{
            RateDto rateDto = rateService.getRateById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy thông tin đánh giá thành công",rateDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("")
    public ResponseEntity<?> addRate(@RequestBody AddRateDto addRateDto){
        try{
            rateService.addRate(addRateDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm đánh giá thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> updateRate(@PathVariable String id,@RequestBody RateDto rateDto){
        try{
            rateService.updateRate(id, rateDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật đánh giá thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> deleteRate(@PathVariable String id) {
        try {
            rateService.deleteRate(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa đánh giá thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}