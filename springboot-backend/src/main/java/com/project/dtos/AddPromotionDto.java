package com.project.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AddPromotionDto {
    private String promotionName;
    private String description;
    private String discountCode;
    private String promotionType;
    private Long discountValue;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date expiredDate;
    private String status;
}
