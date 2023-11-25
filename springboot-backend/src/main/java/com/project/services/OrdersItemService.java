package com.project.services;

import com.project.dtos.AddOrdersItemDto;
import com.project.dtos.OrdersItemDto;
import com.project.entities.OrdersItem;

import java.util.List;

public interface OrdersItemService {
    public OrdersItem findByOrdersItemId(String ordersId, String productId, String size);
    public void addOrdersItem(AddOrdersItemDto addOrdersItemDto);
    public void addOrdersItemList(String ordersId, List<AddOrdersItemDto> addOrdersItemDtoList);

    public void updateStatusRate(Long id);

}
