package com.project.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Payment {
    @Id
    private String id;
    @Column(name = "payment_name")
    private String paymentName;
    private String description;
    private String status;
}
