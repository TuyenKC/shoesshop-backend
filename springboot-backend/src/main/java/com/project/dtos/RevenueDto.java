package com.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class RevenueDto {
    private Double totalRevenue;
    private List<RevenueDateDto> revenueDateList;
}
