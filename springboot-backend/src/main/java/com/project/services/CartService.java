package com.project.services;

import com.project.dtos.CartDto;
import com.project.entities.Cart;
import com.project.entities.CartItem;
import com.project.entities.Users;

public interface CartService {
    public Cart findById(String id);
    public void addCart(Users users);
    public CartDto getCartById(String id);
    public CartDto getCartByUsername(String username);
}
