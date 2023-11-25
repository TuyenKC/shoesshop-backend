package com.project.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Promotion {
    @Id
    private String id;
    @Column(name = "promotion_name")
    private String promotionName;
    private String description;
    @Column(name = "discount_code")
    private String discountCode;
    @Column(name = "promotion_type")
    private String promotionType;
    @Column(name = "discount_value")
    private Long discountValue;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @Column(name = "expired_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date expiredDate;
    private String status;
}
