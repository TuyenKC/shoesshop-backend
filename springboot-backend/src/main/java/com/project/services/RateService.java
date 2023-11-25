package com.project.services;

import com.project.dtos.AddRateDto;
import com.project.dtos.RateDto;

import java.util.List;

public interface RateService {
    public void addRate(AddRateDto addRateDto);
    public RateDto updateRate(String id,RateDto rateDto);
    public void deleteRate(String id);
    public RateDto getRateById(String id);
    public List<RateDto> getAllRate();
}
