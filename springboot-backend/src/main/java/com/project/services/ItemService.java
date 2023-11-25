package com.project.services;

import com.project.dtos.*;
import com.project.entities.Item;

import java.util.List;

public interface ItemService {
    public boolean existedByProductSize(String productId, String size);
    public void addItem(String productId, AddItemDto addItemDto);
    public Item findByProductSize(String productId, String size);
    public void updateItem(UpdateItemDto updateItemDto);
    public void deleteItemByProductId(String productId);
    public void updateItemByOrdersItemList(List<AddOrdersItemDto> addOrdersItemDtos);
    public void updateItemListByProductId(String productId, List<AddItemDto> itemList);
}
