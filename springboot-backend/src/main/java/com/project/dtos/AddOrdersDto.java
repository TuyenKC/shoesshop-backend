package com.project.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
public class AddOrdersDto {
    private String addressReceiver;
    private String nameReceiver;
    private String phoneReceiver;
    private String note;
    private Long shipmentCost;
    private Long discountValue;
    private Long totalPay;
    private Long totalPrice;
    private String status;
    private String paymentStatus;
    private String paymentId;
    private String promotionId;
    private String shipmentId;
    private String usersId;
    private List<AddOrdersItemDto> ordersItems = new ArrayList<>();
}
