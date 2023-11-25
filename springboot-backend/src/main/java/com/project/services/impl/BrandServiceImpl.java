package com.project.services.impl;

import com.project.dtos.AddBrandDto;
import com.project.dtos.BrandDto;
import com.project.dtos.UpdateBrandDto;
import com.project.entities.Brand;
import com.project.entities.Brand;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.mappers.GetDateNow;
import com.project.repositories.BrandRepository;
import com.project.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand findById(String id) {
        Optional<Brand> brand = brandRepository.findById(id);
        if(!brand.isPresent()){
            throw new AppException("Nhãn hiệu không tồn tại","404");
        }
        return brand.get();
    }

    @Override
    public boolean existsByBrandName(String brandName) {
        return brandRepository.existsByBrandName(brandName);
    }

    @Override
    public BrandDto addBrand(AddBrandDto addBrandDto) {
        if(brandRepository.existsByBrandName(addBrandDto.getBrandName())){
            throw new AppException("Tên nhãn hiệu đã tồn tại","409");
        }
        Brand brand = new DtoToEntityMapper().dtoToEntityAddBrand(addBrandDto);
        Brand savedBrand = brandRepository.save(brand);
        return new EntityToDtoMapper().entityToDtoBrand(savedBrand);
    }

    @Override
    public BrandDto updateBrand(String id, UpdateBrandDto updateBrandDto) {
        if(brandRepository.findById(id) == null)
            throw new AppException("Nhãn hiệu không tồn tại", "404");
        Brand brand = brandRepository.findById(id).get();
        if(!updateBrandDto.getBrandName().equals(brand.getBrandName()) && brandRepository.existsByBrandName(updateBrandDto.getBrandName())){
            throw new AppException("Tên nhãn hiệu đã tồn tại","409");
        }
        brand.setStatus(updateBrandDto.getStatus());
        brand.setBrandName(updateBrandDto.getBrandName());
        brand.setModifiedAt(GetDateNow.getDateNow());
        brand.setDescription(updateBrandDto.getDescription());
        Brand savedBrand = brandRepository.save(brand);
        return new EntityToDtoMapper().entityToDtoBrand(savedBrand);
    }

    @Override
    public void deleteBrand(String id) {
        if(brandRepository.findById(id) == null)
            throw new AppException("Nhãn hiệu không tồn tại", "404");
        brandRepository.deleteById(id);
    }

    @Override
    public BrandDto getBrandById(String id) {
        if(!brandRepository.findById(id).isPresent())
            throw new AppException("Nhãn hiệu không tồn tại", "404");
        Brand brand = brandRepository.findById(id).get();
        return new EntityToDtoMapper().entityToDtoBrand(brand);
    }

    @Override
    public List<BrandDto> getAllBrand() {
        List<BrandDto> brandDtos = brandRepository.findAll().stream()
                .map(brand -> new EntityToDtoMapper().entityToDtoBrand(brand))
                .collect(Collectors.toList());
        return brandDtos;
    }

    @Override
    public List<BrandDto> getAllBrandValid() {
        List<BrandDto> brandDtos = brandRepository.findAll().stream()
                .filter(brand -> "Active".equals(brand.getStatus()))
                .map(brand -> new EntityToDtoMapper().entityToDtoBrand(brand))
                .collect(Collectors.toList());
        return brandDtos;
    }
}
