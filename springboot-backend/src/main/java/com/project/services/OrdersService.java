package com.project.services;

import com.project.dtos.*;
import com.project.entities.Orders;

import java.util.Date;
import java.util.List;

public interface OrdersService {
    public Orders findById(String id);
    public void addOrders(AddOrdersDto addOrdersDto, String action);
    public void updateOrders(String id, UpdateOrdersDto updateOrdersDto);
    public void deleteOrders(String id);
    public OrdersDto getOrdersById(String id);
    public List<OrdersDto> getAllOrders();
    public List<OrdersDto> getAllOrdersByUsername(String userId);
    public RevenueDto getRevenueByProduct(String productName);
    public RevenueDto getRevenueByBrand(String brandName);
    public RevenueDto getRevenueByDate(String startDate, String endDate);

}
