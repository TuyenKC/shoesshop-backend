package com.project.entities;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class OrdersItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    private Orders orders;

    private int quantity;
    @Column(name = "status_rate")
    private String statusRate;
}
