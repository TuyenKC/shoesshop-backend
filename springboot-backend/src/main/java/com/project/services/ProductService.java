package com.project.services;

import com.project.dtos.AddOrdersItemDto;
import com.project.dtos.AddProductDto;
import com.project.dtos.ProductDto;
import com.project.dtos.UpdateProductDto;
import com.project.entities.Product;

import java.util.List;

public interface ProductService {
    public Product save(Product product);
    public Product findById(String id);
    public boolean existedByProductName(String productName);
    public ProductDto addProduct(AddProductDto addProductDto);
    public ProductDto updateProduct(String id, UpdateProductDto updateProductDto);
    public void deleteProduct(String id);
    public ProductDto getProductById(String id);
    public List<ProductDto> getAllProduct();
    public List<ProductDto> getAllProductValid();

    public List<ProductDto> getMostViewProductList();
    public List<ProductDto> getMostSoldProductList();
    public List<ProductDto> getMostRateProductList();
    public void updateStockByOrdersItemList(List<AddOrdersItemDto> addOrdersItemDtoList);
}
