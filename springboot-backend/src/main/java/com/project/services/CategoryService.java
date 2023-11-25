package com.project.services;

import com.project.dtos.AddCategoryDto;
import com.project.dtos.CategoryDto;
import com.project.dtos.UpdateCategoryDto;
import com.project.entities.Category;

import java.util.List;

public interface CategoryService {
    public Category findById(String id);
    public Category findByCategoryName(String name);
    public CategoryDto addCategory(AddCategoryDto addCategoryDto);
    public CategoryDto updateCategory(String id, UpdateCategoryDto updateCategoryDto);
    public void deleteCategory(String id);
    public CategoryDto getCategoryById(String id);
    public List<CategoryDto> getAllCategory();
    public List<CategoryDto> getAllCategoryValid();

}
