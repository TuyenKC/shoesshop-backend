package com.project.services;

import com.project.dtos.AddCartItemDto;
import com.project.dtos.AddOrdersItemDto;
import com.project.dtos.CartItemDto;
import com.project.dtos.UpdateCartItemDto;
import com.project.entities.CartItem;

import java.util.List;

public interface CartItemService {
    public CartItem findByCartItemId(String cartId, String productId, String size);
    public void addCartItem(AddCartItemDto addCartItemDto);
    public void updateCartItem(Long id, UpdateCartItemDto updateCartItemDto);
    public void deleteCartItem(Long id);
    public void deleteCartItemList(List<AddOrdersItemDto> addOrdersItemDtoList);
}
