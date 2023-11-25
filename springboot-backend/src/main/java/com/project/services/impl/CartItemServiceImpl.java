package com.project.services.impl;

import com.project.dtos.*;
import com.project.entities.CartItem;
import com.project.entities.Item;
import com.project.exceptions.AppException;
import com.project.repositories.CartItemRepository;
import com.project.services.CartItemService;
import com.project.services.CartService;
import com.project.services.ItemService;
import com.project.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    @Lazy
    private OrdersService ordersService;
    @Autowired
    private ItemService itemService;

    @Override
    public CartItem findByCartItemId(String cartId, String productId, String size) {
        List<CartItem> cartItems = cartItemRepository.findAll();
        int test = cartItems.size();
        for(CartItem cartItem: cartItems){
            if(cartItem.getCart() != null && cartItem.getCart().getId().equals(cartId) && cartItem.getItem().getProduct().getId().equals(productId)
                    && cartItem.getItem().getSize().name().equals(size)){
                return cartItem;
            }
        }
        return null;
    }
    @Override
    public void addCartItem(AddCartItemDto addCartItemDto) {
        CartItem existedCartItem = null;
        if(addCartItemDto.getCartId() != null)
            existedCartItem = findByCartItemId(addCartItemDto.getCartId(), addCartItemDto.getProductId(), addCartItemDto.getSize());
        if(existedCartItem != null){
            existedCartItem.setQuantity(Math.min(existedCartItem.getQuantity() + addCartItemDto.getQuantity(), addCartItemDto.getQuantityInStock()));
            cartItemRepository.save(existedCartItem);
        }else {
            CartItem cartItem = new CartItem();
            cartItem.setQuantity(addCartItemDto.getQuantity());
            if (addCartItemDto.getCartId() != null) {
                cartItem.setCart(cartService.findById(addCartItemDto.getCartId()));
            }
            cartItem.setItem(itemService.findByProductSize(addCartItemDto.getProductId(), addCartItemDto.getSize()
            ));
            cartItemRepository.save(cartItem);
        }
    }

    @Override
    public void updateCartItem(Long id, UpdateCartItemDto updateCartItemDto) {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if(!cartItem.isPresent())
            throw new AppException("404","Vật phẩm giỏ hàng không tồn tại");
        CartItem updateCartItem = cartItem.get();
        updateCartItem.setQuantity(updateCartItemDto.getQuantity());
        cartItemRepository.save(updateCartItem);
    }

    @Override
    public void deleteCartItem(Long id) {
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if(!cartItem.isPresent())
            throw new AppException("404","Vật phẩm giỏ hàng không tồn tại");
        cartItemRepository.deleteById(id);
    }

    @Override
    public void deleteCartItemList(List<AddOrdersItemDto> addOrdersItemDtoList) {
        for(AddOrdersItemDto addOrdersItemDto: addOrdersItemDtoList){
            deleteCartItem(addOrdersItemDto.getId());
        }
    }

}
