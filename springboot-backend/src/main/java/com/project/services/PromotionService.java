package com.project.services;

import com.project.dtos.AddPromotionDto;
import com.project.dtos.PromotionDto;
import com.project.dtos.SelectPromotionDto;
import com.project.dtos.UpdatePromotionDto;
import com.project.entities.Promotion;

import java.util.List;

public interface PromotionService {
    public Promotion findById(String id);
    public PromotionDto addPromotion(AddPromotionDto addPromotionDto);
    public PromotionDto updatePromotion(String id, UpdatePromotionDto updatePromotionDto);
    public void deletePromotion(String id);
    public PromotionDto getPromotionById(String id);
    public List<PromotionDto> getAllPromotion();
    public List<SelectPromotionDto> getAllPromotionValid();

}
