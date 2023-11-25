package com.project.services.impl;

import com.project.dtos.*;
import com.project.entities.*;
import com.project.entities.Orders;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.mappers.GetDateNow;
import com.project.repositories.OrdersRepository;
import com.project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PromotionService promotionService;
    @Autowired
    private ShipmentService shipmentService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private OrdersItemService ordersItemService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Override
    public Orders findById(String id) {
        Optional<Orders> orders = ordersRepository.findById(id);
        if(!orders.isPresent()){
            throw new AppException("Đơn hàng không tồn tại","404");
        }
        return orders.get();
    }
    @Transactional
    @Override
    public void addOrders(AddOrdersDto addOrdersDto, String action) {
        Orders orders = new DtoToEntityMapper().dtoToEntityAddOrders(addOrdersDto);
        orders.setPayment(paymentService.findById(addOrdersDto.getPaymentId()));
        orders.setPromotion(promotionService.findById(addOrdersDto.getPromotionId()));
        orders.setShipment(shipmentService.findById(addOrdersDto.getShipmentId()));
        orders.setUsers(usersService.findById(addOrdersDto.getUsersId()));
        Orders savedOrders = ordersRepository.save(orders);
        if(action.equals("make")){
            cartItemService.deleteCartItemList(addOrdersDto.getOrdersItems());
        }
        ordersItemService.addOrdersItemList(savedOrders.getId(), addOrdersDto.getOrdersItems());
        itemService.updateItemByOrdersItemList(addOrdersDto.getOrdersItems());
        productService.updateStockByOrdersItemList(addOrdersDto.getOrdersItems());
    }

    @Override
    public void updateOrders(String id, UpdateOrdersDto updateOrdersDto) {
        if(ordersRepository.findById(id) == null)
            throw new AppException("Đơn hàng không tồn tại", "404");
        Orders orders = ordersRepository.findById(id).get();
        orders.setModifiedAt(GetDateNow.getDateNow());
        orders.setStatus(updateOrdersDto.getStatus());
        if(updateOrdersDto.getStatus().equals("Giao thành công")){
            orders.setPaymentStatus("Đã thanh toán");
        }
        Orders savedOrders = ordersRepository.save(orders);
    }

    @Override
    public void deleteOrders(String id) {
        if(ordersRepository.findById(id) == null)
            throw new AppException("Đơn hàng không tồn tại", "404");
        ordersRepository.deleteById(id);
    }

    @Override
    public OrdersDto getOrdersById(String id) {
        if(!ordersRepository.findById(id).isPresent())
            throw new AppException("Đơn hàng không tồn tại", "404");
        Orders orders = ordersRepository.findById(id).get();
        return new EntityToDtoMapper().entityToDtoOrders(orders);
    }

    @Override
    public List<OrdersDto> getAllOrders() {
        List<OrdersDto> ordersDtos = ordersRepository.findAll().stream()
                .map(orders -> new EntityToDtoMapper().entityToDtoOrders(orders))
                .collect(Collectors.toList());
        return ordersDtos;
    }

    @Override
    public List<OrdersDto> getAllOrdersByUsername(String username) {
        List<Orders> ordersList = ordersRepository.findAll();
        List<OrdersDto> ordersDtoList = new ArrayList<>();
        for(Orders orders: ordersList){
            if(orders.getUsers().getUsername().equals(username)){
                ordersDtoList.add(new EntityToDtoMapper().entityToDtoOrders(orders));
            }
        }
        return ordersDtoList;
    }

    @Override
    public RevenueDto getRevenueByProduct(String productName) {
        List<RevenueDateDto> revenueDateDtoList = new ArrayList<>();
        if(!productService.existedByProductName(productName)){
            throw new AppException("Sản phẩm không tồn tại","404");
        }
        Double totalRevenue = 0.0d;
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Orders> ordersList = ordersRepository.findAll();
        for(int i = 0; i <= 6; i++){
            Date date = calendar.getTime();
            String formattedDateStr = sdf.format(date);
            try {
                Date formattedDate = sdf.parse(formattedDateStr);
                Double revenue = 0.0d;
                for(Orders orders: ordersList){
                    for(OrdersItem ordersItem: orders.getOrdersItems()){
                        if(orders.getStatus().equals("Giao thành công") && orders.getCreatedAt().equals(formattedDate) && ordersItem.getItem().getProduct().getProductName().equalsIgnoreCase(productName)){
                            revenue += ordersItem.getItem().getProduct().getSalePrice() * ordersItem.getQuantity();
                        }
                    }
                }
                revenueDateDtoList.add(new RevenueDateDto(date,revenue));
                totalRevenue += revenue;
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        Collections.reverse(revenueDateDtoList);
        return new RevenueDto(totalRevenue, revenueDateDtoList);
    }

    @Override
    public RevenueDto getRevenueByBrand(String brandName) {
        List<RevenueDateDto> revenueDateDtoList = new ArrayList<>();
        if(!brandService.existsByBrandName(brandName)){
            throw new AppException("Nhãn hiệu không tồn tại","404");
        }
        Double totalRevenue = 0.0d;
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Orders> ordersList = ordersRepository.findAll();
        for(int i = 0; i <= 6; i++){
            Date date = calendar.getTime();
            String formattedDateStr = sdf.format(date);
            try {
                Date formattedDate = sdf.parse(formattedDateStr);
                Double revenue = 0.0d;
                for(Orders orders: ordersList){
                    for(OrdersItem ordersItem: orders.getOrdersItems()){
                        if(orders.getStatus().equals("Giao thành công") && orders.getCreatedAt().equals(formattedDate) && ordersItem.getItem().getProduct().getBrand().getBrandName().equalsIgnoreCase(brandName)){
                            revenue += ordersItem.getItem().getProduct().getSalePrice() * ordersItem.getQuantity();
                        }
                    }
                }
                revenueDateDtoList.add(new RevenueDateDto(date,revenue));
                totalRevenue += revenue;
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        Collections.reverse(revenueDateDtoList);
        return new RevenueDto(totalRevenue, revenueDateDtoList);
    }

    @Override
    public RevenueDto getRevenueByDate(String startDate, String endDate) {
        List<RevenueDateDto> revenueDateDtoList = new ArrayList<>();
        Double totalRevenue = 0.0d;
        Date formattedEndDate = new Date();
        Date formattedStartDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            formattedStartDate = sdf.parse(startDate);
            formattedEndDate = sdf.parse(endDate);
        } catch (ParseException e) {
            throw new AppException("Ngày không hợp lệ","404");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formattedEndDate);
        List<Orders> ordersList = ordersRepository.findAll();
        while(!calendar.getTime().before(formattedStartDate)){
            Date date = calendar.getTime();
            String formattedDateStr = sdf.format(date);
            try {
                Date formattedDate = sdf.parse(formattedDateStr);
                Double revenue = 0.0d;
                for(Orders orders: ordersList){
                    for(OrdersItem ordersItem: orders.getOrdersItems()){
                        if(orders.getStatus().equals("Giao thành công") && orders.getCreatedAt().equals(formattedDate)){
                            revenue += ordersItem.getItem().getProduct().getSalePrice() * ordersItem.getQuantity();
                        }
                    }
                }
                revenueDateDtoList.add(new RevenueDateDto(date,revenue));
                totalRevenue += revenue;
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        Collections.reverse(revenueDateDtoList);
        return new RevenueDto(totalRevenue, revenueDateDtoList);
    }
}
