package com.project.services.impl;

import com.project.dtos.CategoryProductDto;
import com.project.dtos.CategoryProductDto;
import com.project.entities.Category;
import com.project.entities.CategoryProduct;
import com.project.entities.CategoryProductKey;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.repositories.CategoryProductRepository;
import com.project.services.CategoryProductService;
import com.project.services.ProductService;
import com.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryProductServiceImpl implements CategoryProductService {
    private final CategoryProductRepository categoryProductRepository;
    private final ProductService productService;
    private final CategoryService categoryService;

    public CategoryProductServiceImpl(CategoryProductRepository categoryProductRepository, @Lazy ProductService productService, CategoryService categoryService) {
        this.categoryProductRepository = categoryProductRepository;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void addCategoryProduct(String productId, String categoryName) {
        CategoryProduct categoryProduct = new CategoryProduct();
        Category category = categoryService.findByCategoryName(categoryName);
        categoryProduct.setCategoryProductKey(new CategoryProductKey(productId,category.getId()));
        categoryProduct.setProduct(productService.findById(productId));
        categoryProduct.setCategory(categoryService.findById(category.getId()));
        categoryProductRepository.save(categoryProduct);
    }

    @Override
    public void deleteCategoryProduct(CategoryProductDto categoryProductDto) {
        CategoryProductKey categoryProductKey = new CategoryProductKey(categoryProductDto.getProductId(),categoryProductDto.getCategoryId());
        if(!categoryProductRepository.findById(categoryProductKey).isPresent()){
            throw new AppException("404","Danh mục sản phẩm không tồn tại");
        }
        categoryProductRepository.deleteById(categoryProductKey);
    }
    @Override
    public void deleteByProductId(String productId) {
        List<CategoryProduct> categoryProductList = categoryProductRepository.findAll();
        categoryProductList.removeIf(categoryProduct -> !categoryProduct.getProduct().getId().equals(productId));
        categoryProductRepository.deleteAll(categoryProductList);
    }

    @Override
    public void addCategoryProductByListCategoryName(String productId, List<String> categoryNameList) {
        deleteByProductId(productId);
        for(String categoryName: categoryNameList)
            addCategoryProduct(productId, categoryName);
    }

}
