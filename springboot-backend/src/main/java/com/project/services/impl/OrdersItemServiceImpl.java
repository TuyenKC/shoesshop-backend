package com.project.services.impl;

import com.project.dtos.AddOrdersItemDto;
import com.project.dtos.OrdersItemDto;
import com.project.entities.OrdersItem;
import com.project.exceptions.AppException;
import com.project.repositories.OrdersItemRepository;
import com.project.services.ItemService;
import com.project.services.OrdersItemService;
import com.project.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrdersItemServiceImpl implements OrdersItemService {
    @Autowired
    private OrdersItemRepository ordersItemRepository;
    @Autowired
    @Lazy
    private OrdersService ordersService;
    @Autowired
    private ItemService itemService;
    @Override
    public OrdersItem findByOrdersItemId(String ordersId, String productId, String size) {
        List<OrdersItem> ordersItems = ordersItemRepository.findAll();
        for(OrdersItem ordersItem: ordersItems){
            if(ordersItem.getOrders() != null && ordersItem.getOrders().getId().equals(ordersId) && ordersItem.getItem().getProduct().getId().equals(productId)
                    && ordersItem.getItem().getSize().name().equals(size)){
                return ordersItem;
            }
        }
        return null;
    }

    @Override
    public void addOrdersItem(AddOrdersItemDto addOrdersItemDto) {
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setQuantity(addOrdersItemDto.getQuantity());
        if (addOrdersItemDto.getOrdersId() != null) {
            ordersItem.setOrders(ordersService.findById(addOrdersItemDto.getOrdersId()));
        }
        ordersItem.setStatusRate("Chưa đánh giá");
        ordersItem.setItem(itemService.findByProductSize(addOrdersItemDto.getProductId(), addOrdersItemDto.getSize()
        ));
        ordersItemRepository.save(ordersItem);
    }

    @Override
    public void addOrdersItemList(String ordersId, List<AddOrdersItemDto> addOrdersItemDtoList) {
        for(AddOrdersItemDto addOrdersItemDto: addOrdersItemDtoList){
            addOrdersItemDto.setOrdersId(ordersId);
            addOrdersItem(addOrdersItemDto);
        }
    }

    @Override
    public void updateStatusRate(Long id) {
        Optional<OrdersItem> ordersItem = ordersItemRepository.findById(id);
        if(!ordersItem.isPresent())
            throw new AppException("404","Vật phẩm đơn hàng không tồn tại");
        OrdersItem updateOrdersItem = ordersItem.get();
        updateOrdersItem.setStatusRate("Đã đánh giá");
        ordersItemRepository.save(updateOrdersItem);
    }
}
