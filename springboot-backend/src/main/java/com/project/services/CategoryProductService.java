package com.project.services;

import com.project.dtos.CategoryProductDto;

import java.util.List;

public interface CategoryProductService {
    public void deleteByProductId(String productId);
    public void addCategoryProductByListCategoryName(String productId, List<String> categoryNameList);
    public void addCategoryProduct(String productId, String categoryName);
    public void deleteCategoryProduct(CategoryProductDto categoryProductDto);
}
