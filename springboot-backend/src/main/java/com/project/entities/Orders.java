package com.project.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    private String id;
    @Column(name = "address_receiver")
    private String addressReceiver;
    @Column(name = "name_receiver")
    private String nameReceiver;
    @Column(name = "phone_receiver")
    private String phoneReceiver;
    private String note;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedAt;
    @Column(name = "total_price")
    private Long totalPrice;
    @Column(name = "shipment_cost")
    private Long shipmentCost;
    @Column(name = "discount_value")
    private Long discountValue;
    @Column(name = "total_pay")
    private Long totalPay;
    @Column(name = "payment_status")
    private String paymentStatus;
    private String status;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
    @OneToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
    @OneToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;
    @OneToMany(mappedBy = "orders")
    private List<OrdersItem> ordersItems;
}
