package com.project.services.impl;

import com.project.dtos.AddRateDto;
import com.project.dtos.RateDto;
import com.project.entities.Rate;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.repositories.RateRepository;
import com.project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrdersItemService ordersItemService;

    @Override
    public void addRate(AddRateDto addRateDto) {
        Rate rate = new DtoToEntityMapper().dtoToEntityAddRate(addRateDto);
        rate.setUsers(usersService.findById(addRateDto.getUserId()));
        rate.setProduct(productService.findById(addRateDto.getProductId()));
        ordersItemService.updateStatusRate(addRateDto.getOrdersItemId());
        Rate savedRate = rateRepository.save(rate);
    }

    @Override
    public RateDto updateRate(String id, RateDto rateDto) {
        if(rateRepository.findById(id) == null)
            throw new AppException("Đánh giá không tồn tại", "404");
        Rate rate = rateRepository.findById(id).get();
        rate.setStar(rateDto.getStar());
        rate.setUsers(usersService.findById(rateDto.getUserId()));
        rate.setProduct(productService.findById(rateDto.getProductId()));
        Rate savedRate = rateRepository.save(rate);
        return new EntityToDtoMapper().entityToDtoRate(savedRate);
    }

    @Override
    public void deleteRate(String id) {
        if(rateRepository.findById(id) == null)
            throw new AppException("Đánh giá không tồn tại", "404");
        rateRepository.deleteById(id);
    }

    @Override
    public RateDto getRateById(String id) {
        if(!rateRepository.findById(id).isPresent())
            throw new AppException("Đánh giá không tồn tại", "404");
        Rate rate = rateRepository.findById(id).get();
        return new EntityToDtoMapper().entityToDtoRate(rate);
    }

    @Override
    public List<RateDto> getAllRate() {
        List<RateDto> rateDtos = rateRepository.findAll().stream()
                .map(rate -> new EntityToDtoMapper().entityToDtoRate(rate))
                .collect(Collectors.toList());
        return rateDtos;
    }
}
