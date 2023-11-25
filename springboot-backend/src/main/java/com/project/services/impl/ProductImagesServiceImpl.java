package com.project.services.impl;

import com.project.dtos.AddProductImagesDto;
import com.project.dtos.ProductImagesDto;
import com.project.dtos.ProductImagesDto;
import com.project.entities.ProductImages;
import com.project.entities.ProductImagesKey;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.repositories.ProductImagesRepository;
import com.project.services.ProductImagesService;
import com.project.services.ProductService;
import com.project.services.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImagesServiceImpl implements ProductImagesService {
    @Autowired
    private ProductImagesRepository productImagesRepository;
    @Lazy
    @Autowired
    private ProductService productService;
    @Autowired
    private ImagesService imagesService;
    @Override
    public void addProductImages(AddProductImagesDto addProductImagesDto) {
        ProductImages productImages = new ProductImages();
        productImages.setProductImagesKey(new ProductImagesKey(addProductImagesDto.getProductId(),addProductImagesDto.getImagesId()));
        productImages.setProduct(productService.findById(addProductImagesDto.getProductId()));
        productImages.setImages(imagesService.findById(addProductImagesDto.getImagesId()));
        productImagesRepository.save(productImages);
    }

    @Override
    public ProductImagesDto updateProductImages(ProductImagesDto productImagesDto) {
        ProductImages productImages = productImagesRepository.findById(new ProductImagesKey(productImagesDto.getProductId(),productImagesDto.getImagesId())).get();
        productImagesRepository.save(productImages);
        return productImagesDto;
    }

    @Override
    public void addProductImagesByListImagesId(String productId, List<String> imagesIdList) {
        deleteProductImagesByProductId(productId);
        for(String imageId: imagesIdList)
            addProductImages(new AddProductImagesDto(productId,imageId));
    }

    @Override
    public void deleteProductImages(ProductImagesDto productImagesDto) {
        ProductImagesKey productImagesKey = new ProductImagesKey(productImagesDto.getProductId(),productImagesDto.getImagesId());
        if(!productImagesRepository.findById(productImagesKey).isPresent()){
            throw new AppException("404","Hình ảnh sản phẩm không tồn tại");
        }
        productImagesRepository.deleteById(productImagesKey);
    }

    @Override
    public void deleteProductImagesByProductId(String productId) {
        List<ProductImages> productImagesList = productImagesRepository.findAll();
        for(ProductImages productImages: productImagesList){
            if(productImages.getProduct().getId().equals(productId)){
                productImagesRepository.delete(productImages);
            }
        }
    }

}
