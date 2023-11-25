package com.project.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Item {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Enumerated(EnumType.STRING)
    private ESize size;
    private int quantityInStock;
    @OneToMany(mappedBy = "item")
    private List<CartItem> cartItems;
}
