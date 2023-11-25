package com.project.services.impl;

import com.project.dtos.CartDto;
import com.project.entities.Cart;
import com.project.entities.Users;
import com.project.exceptions.AppException;
import com.project.mappers.EntityToDtoMapper;
import com.project.repositories.CartRepository;
import com.project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart findById(String id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if(!cart.isPresent()){
            throw new AppException("Giỏ hàng không tồn tại","404");
        }
        return cart.get();
    }

    @Override
    public void addCart(Users users) {
        Cart cart = new Cart();
        cart.setId(UUID.randomUUID().toString());
        cart.setUsers(users);
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCartById(String id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if(!cart.isPresent()){
            throw new AppException("Giỏ hàng không tồn tại","404");
        }
        return new EntityToDtoMapper().entityToDtoCart(cart.get());
    }

    @Override
    public CartDto getCartByUsername(String username) {
        List<Cart> carts = cartRepository.findAll();
        for(Cart cart: carts){
            if(cart.getUsers().getUsername().equals(username))
                return new EntityToDtoMapper().entityToDtoCart(cart);
        }
        throw new AppException("Giỏ hàng không tồn tại","404");
    }
}
