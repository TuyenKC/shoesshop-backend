package com.project.services;

import com.project.dtos.AddProductImagesDto;
import com.project.dtos.ProductImagesDto;

import java.util.List;

public interface ProductImagesService {
    public void addProductImages(AddProductImagesDto addProductImagesDto);
    public ProductImagesDto updateProductImages(ProductImagesDto productImagesDto);
    public void addProductImagesByListImagesId(String productId, List<String> imagesIdList);
    public void deleteProductImages(ProductImagesDto productImagesDto);
    public void deleteProductImagesByProductId(String productId);
}
