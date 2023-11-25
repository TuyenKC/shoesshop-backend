package com.project.dtos;

import lombok.Data;

@Data
public class AddPaymentDto {
    private String paymentName;
    private String description;
    private String status;
}
