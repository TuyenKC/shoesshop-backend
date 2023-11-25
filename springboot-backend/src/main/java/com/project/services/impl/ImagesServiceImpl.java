package com.project.services.impl;

import com.project.dtos.AddImagesDto;
import com.project.dtos.ImagesDto;
import com.project.dtos.ImagesDto;
import com.project.entities.Images;
import com.project.entities.Images;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.repositories.ImagesRepository;
import com.project.services.ImagesService;
import com.project.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private UsersService usersService;

    @Override
    public Images findById(String id) {
        Optional<Images> images = imagesRepository.findById(id);
        if(!images.isPresent()){
            throw new AppException("Hình ảnh không tồn tại","404");
        }
        return images.get();
    }

    @Override
    public ImagesDto addImages(AddImagesDto addImagesDto) {
        Images images = new DtoToEntityMapper().dtoToEntityAddImages(addImagesDto);
        images.setUsers(usersService.findById(addImagesDto.getUsersId()));
        Images savedImages = imagesRepository.save(images);
        return new EntityToDtoMapper().entityToDtoImages(savedImages);
    }

//    @Override
//    public ImagesDto updateImages(String id, ImagesDto imagesDto) {
//        if(imagesRepository.findById(id) == null)
//            throw new AppException("Hình ảnh không tồn tại", "404");
//        Images images = imagesRepository.findById(id).get();
//        images.setLink(imagesDto.getLink());
//        images.setTitle(imagesDto.getTitle());
//        images.setSize(imagesDto.getSize());
//        images.setTypeImages(imagesDto.getTypeImages());
//        Images savedImages = imagesRepository.save(images);
//        return new EntityToDtoMapper().entityToDtoImages(savedImages);
//    }

    @Override
    public void deleteImages(String id) {
        if(imagesRepository.findById(id) == null)
            throw new AppException("Hình ảnh không tồn tại", "404");
        imagesRepository.deleteById(id);
    }

    @Override
    public ImagesDto getImagesById(String id) {
        if(!imagesRepository.findById(id).isPresent())
            throw new AppException("Hình ảnh không tồn tại", "404");
        Images images = imagesRepository.findById(id).get();
        return new EntityToDtoMapper().entityToDtoImages(images);
    }

    @Override
    public List<ImagesDto> getAllImages() {
        List<ImagesDto> imagesDtos = imagesRepository.findAll().stream()
                .map(images -> new EntityToDtoMapper().entityToDtoImages(images))
                .collect(Collectors.toList());
        return imagesDtos;
    }
}
