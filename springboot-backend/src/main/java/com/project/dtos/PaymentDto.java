package com.project.dtos;

import lombok.Data;

import javax.persistence.Column;
@Data
public class PaymentDto {
    private String id;
    private String paymentName;
    private String description;
    private String status;
}
