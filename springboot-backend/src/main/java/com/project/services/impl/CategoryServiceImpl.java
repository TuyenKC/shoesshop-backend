package com.project.services.impl;

import com.project.dtos.*;
import com.project.dtos.CategoryDto;
import com.project.entities.Category;
import com.project.entities.Category;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.mappers.GetDateNow;
import com.project.repositories.CategoryRepository;
import com.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(String id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(!category.isPresent()){
            throw new AppException("Danh mục không tồn tại","404");
        }
        return category.get();
    }
    @Override
    public Category findByCategoryName(String name) {
        Optional<Category> category = categoryRepository.findByCategoryName(name);
        if(!category.isPresent()){
            throw new AppException("Danh mục không tồn tại","404");
        }
        return category.get();
    }
    @Override
    public CategoryDto addCategory(AddCategoryDto addCategoryDto) {
        if(categoryRepository.existsByCategoryName(addCategoryDto.getCategoryName())){
            throw new AppException("Tên danh mục đã tồn tại","409");
        }
        Category category = new DtoToEntityMapper().dtoToEntityAddCategory(addCategoryDto);
        Category savedCategory = categoryRepository.save(category);
        return new EntityToDtoMapper().entityToDtoCategory(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(String id, UpdateCategoryDto updateCategoryDto) {
        if(categoryRepository.findById(id) == null)
            throw new AppException("Danh mục không tồn tại", "404");
        Category category = categoryRepository.findById(id).get();
        if(!updateCategoryDto.getCategoryName().equals(category.getCategoryName()) && categoryRepository.existsByCategoryName(updateCategoryDto.getCategoryName())){
            throw new AppException("Tên danh mục đã tồn tại","409");
        }
        category.setCategoryName(updateCategoryDto.getCategoryName());
        category.setStatus(updateCategoryDto.getStatus());
        category.setModifiedAt(GetDateNow.getDateNow());
        Category savedCategory = categoryRepository.save(category);
        return new EntityToDtoMapper().entityToDtoCategory(savedCategory);
    }

    @Override
    public void deleteCategory(String id) {
        if(categoryRepository.findById(id) == null)
            throw new AppException("Danh mục không tồn tại", "404");
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto getCategoryById(String id) {
        if(!categoryRepository.findById(id).isPresent())
            throw new AppException("Danh mục không tồn tại", "404");
        Category category = categoryRepository.findById(id).get();
        return new EntityToDtoMapper().entityToDtoCategory(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<CategoryDto> categoryDtos = categoryRepository.findAll().stream()
                .map(category -> new EntityToDtoMapper().entityToDtoCategory(category))
                .collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public List<CategoryDto> getAllCategoryValid() {
        List<CategoryDto> categoryDtos = categoryRepository.findAll().stream()
                .filter(category -> "Active".equals(category.getStatus()))
                .map(category -> new EntityToDtoMapper().entityToDtoCategory(category))
                .collect(Collectors.toList());
        return categoryDtos;
    }
}
