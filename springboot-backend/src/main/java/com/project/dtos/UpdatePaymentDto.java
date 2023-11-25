package com.project.dtos;

import lombok.Data;

@Data
public class UpdatePaymentDto {
    private String paymentName;
    private String description;
    private String status;
}
