package com.project.dtos;

import com.project.entities.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrdersDto {
    private String id;
    private String addressReceiver;
    private String nameReceiver;
    private String phoneReceiver;
    private String note;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedAt;
    private Long shipmentCost;
    private Long discountValue;
    private Long totalPay;
    private Long totalPrice;
    private String status;
    private String paymentStatus;
    private String paymentName;
    private String promotionName;
    private String shipmentUnit;
    private List<OrdersItemDto> ordersItems = new ArrayList<>();
}
