package com.project.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public Cart(String id) {
        this.id = id;
    }
}
