package com.project.services.impl;

import com.project.dtos.AddPromotionDto;
import com.project.dtos.PromotionDto;
import com.project.dtos.SelectPromotionDto;
import com.project.dtos.UpdatePromotionDto;
import com.project.entities.Promotion;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.repositories.PromotionRepository;
import com.project.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public Promotion findById(String id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        if(!promotion.isPresent()){
            throw new AppException("Khuyến mại không tồn tại","404");
        }
        return promotion.get();
    }

    @Override
    public PromotionDto addPromotion(AddPromotionDto addPromotionDto) {
        if(promotionRepository.existsByPromotionName(addPromotionDto.getPromotionName())){
            throw new AppException("Tên khuyến mại đã tồn tại","409");
        }
        if(promotionRepository.existsByDiscountCode(addPromotionDto.getDiscountCode())){
            throw new AppException("Mã code khuyến mại đã tồn tại","409");
        }
        Promotion promotion = new DtoToEntityMapper().dtoToEntityAddPromotion(addPromotionDto);
        Promotion savedPromotion = promotionRepository.save(promotion);
        return new EntityToDtoMapper().entityToDtoPromotion(savedPromotion);
    }

    @Override
    public PromotionDto updatePromotion(String id, UpdatePromotionDto updatePromotionDto) {
        if(promotionRepository.findById(id) == null)
            throw new AppException("Khuyến mại không tồn tại", "404");
        Promotion promotion = promotionRepository.findById(id).get();
        if(!updatePromotionDto.getPromotionName().equals(promotion.getPromotionName()) && promotionRepository.existsByPromotionName(updatePromotionDto.getPromotionName())){
            throw new AppException("Tên khuyến mại đã tồn tại","409");
        }
        if(!updatePromotionDto.getDiscountCode().equals(promotion.getDiscountCode()) && promotionRepository.existsByDiscountCode(updatePromotionDto.getDiscountCode())){
            throw new AppException("Mã code khuyến mại đã tồn tại","409");
        }
        promotion.setDescription(updatePromotionDto.getDescription());
        promotion.setPromotionName(updatePromotionDto.getPromotionName());
        promotion.setPromotionType(updatePromotionDto.getPromotionType());
        promotion.setStatus(updatePromotionDto.getStatus());
        promotion.setDiscountCode(updatePromotionDto.getDiscountCode());
        promotion.setDiscountValue(updatePromotionDto.getDiscountValue());
        promotion.setStartDate(updatePromotionDto.getStartDate());
        promotion.setExpiredDate(updatePromotionDto.getExpiredDate());
        Promotion savedPromotion = promotionRepository.save(promotion);
        return new EntityToDtoMapper().entityToDtoPromotion(savedPromotion);
    }

    @Override
    public void deletePromotion(String id) {
        if(promotionRepository.findById(id) == null)
            throw new AppException("Khuyến mại không tồn tại", "404");
        promotionRepository.deleteById(id);
    }

    @Override
    public PromotionDto getPromotionById(String id) {
        if(!promotionRepository.findById(id).isPresent())
            throw new AppException("Khuyến mại không tồn tại", "404");
        Promotion promotion = promotionRepository.findById(id).get();
        return new EntityToDtoMapper().entityToDtoPromotion(promotion);
    }

    @Override
    public List<PromotionDto> getAllPromotion() {
        List<PromotionDto> promotionDtos = promotionRepository.findAll().stream()
                .map(promotion -> new EntityToDtoMapper().entityToDtoPromotion(promotion))
                .collect(Collectors.toList());
        return promotionDtos;
    }

    @Override
    public List<SelectPromotionDto> getAllPromotionValid() {
        List<Promotion> promotionList = promotionRepository.findAll();
        List<SelectPromotionDto> filterPromotionDto = new ArrayList<>();
        for(Promotion promotion: promotionList){
            Date date = new Date();
            if(promotion.getStartDate().before(date) && promotion.getExpiredDate().after(date) && promotion.getStatus().equals("Active")){
                filterPromotionDto.add(new EntityToDtoMapper().entityToDtoSelectPromotion(promotion));
            }
        }
        return filterPromotionDto;
    }
}
