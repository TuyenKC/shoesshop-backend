package com.project.services;

import com.project.dtos.AddBrandDto;
import com.project.dtos.BrandDto;
import com.project.dtos.UpdateBrandDto;
import com.project.entities.Brand;

import java.util.List;

public interface BrandService {
    public Brand findById(String id);
    public boolean existsByBrandName(String brandName);
    public BrandDto addBrand(AddBrandDto addBrandDto);
    public BrandDto updateBrand(String id, UpdateBrandDto updateBrandDto);
    public void deleteBrand(String id);
    public BrandDto getBrandById(String id);
    public List<BrandDto> getAllBrand();
    public List<BrandDto> getAllBrandValid();

}
