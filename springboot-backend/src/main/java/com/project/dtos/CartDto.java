package com.project.dtos;

import com.project.entities.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private String id;
    private List<CartItemDto> cartItemList;
    public CartDto(String id) {
        this.id = id;
    }
}
