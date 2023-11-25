package com.project.services;

import com.project.dtos.AddImagesDto;
import com.project.dtos.ImagesDto;
import com.project.entities.Images;

import java.util.List;

public interface ImagesService {
    public Images findById(String id);
    public ImagesDto addImages(AddImagesDto addImagesDto);
//    public ImagesDto updateImages(String id,ImagesDto ImagesDto);
    public void deleteImages(String id);
    public ImagesDto getImagesById(String id);
    public List<ImagesDto> getAllImages();
}
