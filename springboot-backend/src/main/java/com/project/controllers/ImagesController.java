package com.project.controllers;

import com.project.dtos.AddImagesDto;
import com.project.dtos.ImagesDto;
import com.project.dtos.ResponseObject;
import com.project.exceptions.AppException;
import com.project.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ROLE_MODERATOR_INVENTORY') or hasRole('ROLE_STORE_OWNER')")
@RequestMapping("/api/v1/images")
public class ImagesController {
    @Autowired
    private ImagesService imagesService;

    @GetMapping("")
    public ResponseEntity<?> getAllImages(){
        try{
            List<ImagesDto> imagesDtoList = imagesService.getAllImages();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy danh sách hình ảnh thành công", imagesDtoList));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getImagesById(@PathVariable String id){
        try{
            ImagesDto imagesDto = imagesService.getImagesById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Lấy hình ảnh thành công",imagesDto));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
    @PostMapping("")
    public ResponseEntity<?> addImages(@RequestBody AddImagesDto addImagesDto){
        try{
            imagesService.addImages(addImagesDto);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Thêm hình ảnh thành công",""));
        }catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateImages(@PathVariable String id,@RequestBody ImagesDto imagesDto){
//        try{
//            imagesService.updateImages(id, imagesDto);
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200","Cập nhật hình ảnh thành công",""));
//        }catch (AppException e) {
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
//        }
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImages(@PathVariable String id) {
        try {
            imagesService.deleteImages(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("200", "Xóa hình ảnh thành công", ""));
        } catch (AppException e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(e.getCode(), e.getMessage(), ""));
        }
    }
}