package com.project.mappers;

import com.project.dtos.*;
import com.project.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public class EntityToDtoMapper {
    public BrandDto entityToDtoBrand(Brand brand){
        BrandDto brandDto = new BrandDto();
        brandDto.setId(brand.getId());
        brandDto.setBrandName(brand.getBrandName());
        brandDto.setDescription(brand.getDescription());
        brandDto.setCreatedAt(brand.getCreatedAt());
        brandDto.setModifiedAt(brand.getModifiedAt());
        brandDto.setStatus(brand.getStatus());
        if(brand.getProductList() != null)
            brandDto.setProductList(brand.getProductList().stream()
                    .map(product -> entityToDtoProduct(product)).collect(Collectors.toList()));
        return brandDto;
    }
    public ShipmentDto entityToDtoShipment(Shipment shipment){
        ShipmentDto shipmentDto = new ShipmentDto();
        shipmentDto.setId(shipment.getId());
        shipmentDto.setShipmentUnit(shipment.getShipmentUnit());
        shipmentDto.setShipmentCost(shipment.getShipmentCost());
        shipmentDto.setDescription(shipment.getDescription());
        shipmentDto.setStatus(shipment.getStatus());
        return shipmentDto;
    }
    public PaymentDto entityToDtoPayment(Payment payment){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(payment.getId());
        paymentDto.setPaymentName(payment.getPaymentName());
        paymentDto.setDescription(payment.getDescription());
        paymentDto.setStatus(payment.getStatus());
        return paymentDto;
    }

    public UsersDto entityToDtoUsers(Users users){
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setCreatedAt(users.getCreatedAt());
        usersDto.setAddress(users.getAddress());
        usersDto.setModifiedAt(users.getModifiedAt());
        usersDto.setUsername(users.getUsername());
        usersDto.setRole(users.getRole().name());
        usersDto.setStatus(users.isStatus());
        usersDto.setFullname(users.getFullname());
        usersDto.setEmail(users.getEmail());
        usersDto.setPhone(users.getPhone());
        return usersDto;
    }
    public ProductDto entityToDtoProduct(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setModifiedAt(product.getModifiedAt());
        productDto.setPrice(product.getPrice());
        productDto.setSalePrice(product.getSalePrice());
        productDto.setTotalSold(product.getTotalSold());
        productDto.setBrandId(product.getBrand().getId());
        productDto.setBrandName(product.getBrand().getBrandName());
        productDto.setTotalView(product.getTotalView());
        productDto.setStatus(product.getStatus());
        productDto.setAverageRate(0.0);
        if(product.getRateList() != null){
            productDto.setAverageRate(calcAverage(product.getRateList()));
            productDto.setRateList(product.getRateList().stream().map(
                    rate -> entityToDtoRate(rate)
            ).collect(Collectors.toList()));
        }
        if(!(productDto.getAverageRate() > 0)){
            productDto.setAverageRate(0.0);
        }
        if(product.getItems() != null)
            productDto.setItemList(product.getItems().stream().map(
                    item -> entityToDtoItem(item)
            ).collect(Collectors.toList()));
        if(product.getCategoryProducts() != null)
            productDto.setCategoryListName(product.getCategoryProducts().stream().map(
                    categoryProduct -> categoryProduct.getCategory().getCategoryName()
            ).collect(Collectors.toList()));
        if(product.getProductImages() != null)
            productDto.setImagesList(product.getProductImages().stream().map(
                    productImages -> entityToDtoImages(productImages.getImages())
            ).collect(Collectors.toList()));
        return productDto;
    }
    public RateDto entityToDtoRate(Rate rate){
        RateDto rateDto = new RateDto();
        rateDto.setId(rate.getId());
        rateDto.setCreatedAt(rate.getCreatedAt());
        rateDto.setModifiedAt(rate.getModifiedAt());
        rateDto.setStar(rate.getStar());
        rateDto.setUsername(rate.getUsers().getUsername());
        return rateDto;
    }
    public ImagesDto entityToDtoImages(Images images){
        ImagesDto imagesDto = new ImagesDto();
        imagesDto.setId(images.getId());
        imagesDto.setLink(images.getLink());
        return imagesDto;
    }
    public CategoryDto entityToDtoCategory(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setStatus(category.getStatus());
        categoryDto.setCreatedAt(category.getCreatedAt());
        categoryDto.setModifiedAt(category.getModifiedAt());
        return categoryDto;
    }
    public PromotionDto entityToDtoPromotion(Promotion promotion){
        PromotionDto promotionDto = new PromotionDto();
        promotionDto.setId(promotion.getId());
        promotionDto.setDescription(promotion.getDescription());
        promotionDto.setPromotionName(promotion.getPromotionName());
        promotionDto.setPromotionType(promotion.getPromotionType());
        promotionDto.setStatus(promotion.getStatus());
        promotionDto.setDiscountCode(promotion.getDiscountCode());
        promotionDto.setDiscountValue(promotion.getDiscountValue());
        promotionDto.setStartDate(promotion.getStartDate());
        promotionDto.setExpiredDate(promotion.getExpiredDate());
        return promotionDto;
    }
    public OrdersDto entityToDtoOrders(Orders orders){
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId(orders.getId());
        ordersDto.setCreatedAt(orders.getCreatedAt());
        ordersDto.setModifiedAt(orders.getModifiedAt());
        ordersDto.setPaymentStatus(orders.getPaymentStatus());
        ordersDto.setStatus(orders.getStatus());
        ordersDto.setAddressReceiver(orders.getAddressReceiver());
        ordersDto.setNameReceiver(orders.getNameReceiver());
        ordersDto.setPhoneReceiver(orders.getPhoneReceiver());
        ordersDto.setNote(orders.getNote());
        ordersDto.setDiscountValue(orders.getDiscountValue());
        ordersDto.setShipmentCost(orders.getShipmentCost());
        ordersDto.setTotalPay(orders.getTotalPay());
        ordersDto.setTotalPrice(orders.getTotalPrice());
        if(orders.getOrdersItems() != null){
            ordersDto.setOrdersItems(orders.getOrdersItems().stream().map(
                    ordersItem -> entityToDtoOrdersItem(ordersItem)
            ).collect(Collectors.toList()));
        }
        ordersDto.setPaymentName(orders.getPayment().getPaymentName());
        ordersDto.setShipmentUnit(orders.getShipment().getShipmentUnit());
        ordersDto.setPromotionName(orders.getPromotion().getPromotionName());
        return ordersDto;
    }
    public CartItemDto entityToDtoCartItem(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setProductId(cartItem.getItem().getProduct().getId());
        cartItemDto.setQuantity(Math.min(cartItem.getQuantity(),cartItem.getItem().getQuantityInStock()));
        cartItemDto.setProductName(cartItem.getItem().getProduct().getProductName());
        cartItemDto.setSize(cartItem.getItem().getSize().name());
        cartItemDto.setQuantityInStock(cartItem.getItem().getQuantityInStock());
        cartItemDto.setSalePrice(cartItem.getItem().getProduct().getSalePrice());
        cartItemDto.setPrice(cartItem.getItem().getProduct().getPrice());
        cartItemDto.setUrlImagesList(cartItem.getItem().getProduct().getProductImages().stream()
                .map(productImages -> productImages.getImages().getLink()).collect(Collectors.toList()));
        return cartItemDto;
    }
    public CartDto entityToDtoCart(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        if(cart.getCartItems() != null)
            cartDto.setCartItemList(cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getItem().getQuantityInStock() > 0)
                    .map(cartItem -> entityToDtoCartItem(cartItem)
            ).collect(Collectors.toList()));
        return cartDto;
    }
    private ItemDto entityToDtoItem(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setQuantityInStock(item.getQuantityInStock());
        itemDto.setSize(item.getSize().name());
        return itemDto;
    }
    private OrdersItemDto entityToDtoOrdersItem(OrdersItem ordersItem){
        OrdersItemDto ordersItemDto = new OrdersItemDto();
        ordersItemDto.setId(ordersItem.getId());
        ordersItemDto.setProductId(ordersItem.getItem().getProduct().getId());
        ordersItemDto.setQuantity(ordersItem.getQuantity());
        ordersItemDto.setProductName(ordersItem.getItem().getProduct().getProductName());
        ordersItemDto.setSize(ordersItem.getItem().getSize().name());
        ordersItemDto.setQuantityInStock(ordersItem.getItem().getQuantityInStock());
        ordersItemDto.setSalePrice(ordersItem.getItem().getProduct().getSalePrice());
        ordersItemDto.setPrice(ordersItem.getItem().getProduct().getPrice());
        ordersItemDto.setStatusRate(ordersItem.getStatusRate());
        ordersItemDto.setUrlImagesList(ordersItem.getItem().getProduct().getProductImages().stream()
                .map(productImages -> productImages.getImages().getLink()).collect(Collectors.toList()));
        return ordersItemDto;
    }
    public SelectPaymentDto entityToDtoSelectPayment(Payment payment){
        SelectPaymentDto selectPaymentDto = new SelectPaymentDto();
        selectPaymentDto.setId(payment.getId());
        selectPaymentDto.setPaymentName(payment.getPaymentName());
        return selectPaymentDto;
    }
    public SelectPromotionDto entityToDtoSelectPromotion(Promotion promotion){
        SelectPromotionDto selectPromotionDto = new SelectPromotionDto();
        selectPromotionDto.setId(promotion.getId());
        selectPromotionDto.setPromotionName(promotion.getPromotionName());
        selectPromotionDto.setPromotionType(promotion.getPromotionType());
        selectPromotionDto.setDiscountValue(promotion.getDiscountValue());
        return selectPromotionDto;
    }
    public SelectShipmentDto entityToDtoSelectShipment(Shipment shipment){
        SelectShipmentDto selectShipmentDto = new SelectShipmentDto();
        selectShipmentDto.setId(shipment.getId());
        selectShipmentDto.setShipmentCost(shipment.getShipmentCost());
        selectShipmentDto.setShipmentUnit(shipment.getShipmentUnit());
        return selectShipmentDto;
    }

    private Double calcAverage(List<Rate> rateList){
        double sum = 0.0;
        for(Rate rate: rateList){
            sum += rate.getStar();
        }
        return (double) Math.ceil(sum/rateList.size() * 10) / 10;
    }
}
