package com.project.mappers;

import com.project.dtos.*;
import com.project.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class DtoToEntityMapper {

    public Brand dtoToEntityBrand(BrandDto brandDto){
        Brand brand = new Brand();
        brand.setId(brandDto.getId() != null ? brandDto.getId() : UUID.randomUUID().toString());
        brand.setBrandName(brandDto.getBrandName());
        brand.setDescription(brandDto.getDescription() != null ? brandDto.getDescription() : "");
        brand.setCreatedAt(brandDto.getCreatedAt() != null ? brandDto.getCreatedAt() : GetDateNow.getDateNow());
        brand.setModifiedAt(GetDateNow.getDateNow());
        brand.setStatus(brandDto.getStatus() != null ? brandDto.getStatus() : "active");
        return brand;
    }
    public Brand dtoToEntityAddBrand(AddBrandDto addBrandDto){
        Brand brand = new Brand();
        brand.setId(UUID.randomUUID().toString());
        brand.setBrandName(addBrandDto.getBrandName());
        brand.setDescription(addBrandDto.getDescription());
        brand.setCreatedAt(GetDateNow.getDateNow());
        brand.setModifiedAt(GetDateNow.getDateNow());
        brand.setStatus(addBrandDto.getStatus());
        return brand;
    }
//    public Shipment dtoToEntityShipment(ShipmentDto shipmentDto){
//        Shipment shipment = new Shipment();
//        shipment.setId(shipmentDto.getId() != null ? shipmentDto.getId() : UUID.randomUUID().toString());
//        shipment.setShipmentUnit(shipmentDto.getShipmentUnit());
//        shipment.setShipmentCost(shipmentDto.getShipmentCost());
//        shipment.setDescription(shipmentDto.getDescription());
//        shipment.setStatus(shipmentDto.getStatus() != null ? shipmentDto.getStatus() : "active");
//        return shipment;
//    }
    public Shipment dtoToEntityAddShipment(AddShipmentDto addShipmentDto){
        Shipment shipment = new Shipment();
        shipment.setId(UUID.randomUUID().toString());
        shipment.setShipmentUnit(addShipmentDto.getShipmentUnit());
        shipment.setShipmentCost(addShipmentDto.getShipmentCost());
        shipment.setDescription(addShipmentDto.getDescription());
        shipment.setStatus(addShipmentDto.getStatus());
        return shipment;
    }
//    public Payment dtoToEntityPayment(PaymentDto paymentDto){
//        Payment payment = new Payment();
//        payment.setId(paymentDto.getId() != null ? paymentDto.getId() : UUID.randomUUID().toString());
//        payment.setPaymentName(paymentDto.getPaymentName());
//        payment.setDescription(paymentDto.getDescription());
//        payment.setStatus(paymentDto.getStatus() != null ? paymentDto.getStatus() : "active");
//        return payment;
//    }
    public Payment dtoToEntityAddPayment(AddPaymentDto addPaymentDto){
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setPaymentName(addPaymentDto.getPaymentName());
        payment.setDescription(addPaymentDto.getDescription());
        payment.setStatus(addPaymentDto.getStatus() != null ? addPaymentDto.getStatus() : "active");
        return payment;
    }
//    public Users dtoToEntityUsers(UsersDto usersDto){
//        Users users = new Users();
//        users.setId(usersDto.getId() != null ? usersDto.getId() : UUID.randomUUID().toString());
//        users.setUsername(usersDto.getUsername());
//        users.setCreatedAt(usersDto.getCreatedAt() != null ? usersDto.getCreatedAt() : GetDateNow.getDateNow());
//        users.setModifiedAt(GetDateNow.getDateNow());
//        users.setRole(ERole.valueOf(usersDto.getRole()));
//        users.setStatus(usersDto.isStatus());
//        users.setAddress(usersDto.getAddress() != null ? usersDto.getAddress() : "");
//        users.setEmail(usersDto.getEmail() != null ? usersDto.getEmail() : "");
//        users.setPhone(usersDto.getPhone() != null ? usersDto.getPhone() : "");
//        users.setFullname(usersDto.getFullname() != null ? usersDto.getFullname() : "Chua co ten");
//        return users;
//    }
    public Users dtoToEntityAddUsers(AddUsersDto addUsersDto){
        Users users = new Users();
        users.setId(UUID.randomUUID().toString());
        users.setUsername(addUsersDto.getUsername());
        users.setPassword(addUsersDto.getPassword());
        users.setCreatedAt(GetDateNow.getDateNow());
        users.setModifiedAt(GetDateNow.getDateNow());
        users.setRole(ERole.valueOf(addUsersDto.getRole()));
        users.setStatus(addUsersDto.isStatus());
        users.setAddress(addUsersDto.getAddress() != null ? addUsersDto.getAddress() : "");
        users.setEmail(addUsersDto.getEmail() != null ? addUsersDto.getEmail() : "");
        users.setPhone(addUsersDto.getPhone() != null ? addUsersDto.getPhone() : "");
        users.setFullname(addUsersDto.getFullname() != null ? addUsersDto.getFullname() : "Chua co ten");
        return users;
    }
//    public Product dtoToEntityProduct(ProductDto productDto){
//        Product product = new Product();
//        product.setId(productDto.getId() != null ? productDto.getId() : UUID.randomUUID().toString());
//        product.setProductName(productDto.getProductName());
//        product.setDescription(productDto.getDescription());
//        product.setCreatedAt(productDto.getCreatedAt() != null ? productDto.getCreatedAt() : GetDateNow.getDateNow());
//        product.setModifiedAt(GetDateNow.getDateNow());
//        product.setPrice(productDto.getPrice());
//        product.setSalePrice(productDto.getSalePrice());
//        product.setStatus(productDto.getStatus() != null ? productDto.getStatus() : "active");
//        product.setTotalSold(productDto.getTotalSold() != null ? productDto.getTotalSold() : 0);
//        product.setTotalView(productDto.getTotalView() != null ? productDto.getTotalView() : 0);
//        return product;
//    }
    public Product dtoToEntityAddProduct(AddProductDto addProductDto){
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName(addProductDto.getProductName());
        product.setDescription(addProductDto.getDescription());
        product.setCreatedAt(GetDateNow.getDateNow());
        product.setModifiedAt(GetDateNow.getDateNow());
        product.setPrice(addProductDto.getPrice());
        product.setSalePrice(addProductDto.getSalePrice());
        product.setStatus(addProductDto.getStatus() );
        product.setTotalSold(0L);
        product.setTotalView(0L);
        return product;
    }
//    public Rate dtoToEntityRate(RateDto rateDto){
//        Rate rate = new Rate();
//        rate.setId(rateDto.getId() != null ? rateDto.getId() : UUID.randomUUID().toString());
//        rate.setCreatedAt(rateDto.getCreatedAt() != null ? rateDto.getCreatedAt() : GetDateNow.getDateNow());
//        rate.setModifiedAt(GetDateNow.getDateNow());
//        rate.setStar(rateDto.getStar());
//        return rate;
//    }
    public Rate dtoToEntityAddRate(AddRateDto addRateDto){
        Rate rate = new Rate();
        rate.setId(UUID.randomUUID().toString());
        rate.setCreatedAt(GetDateNow.getDateNow());
        rate.setModifiedAt(GetDateNow.getDateNow());
        rate.setStar(addRateDto.getStar());
        return rate;
    }
//    public Images dtoToEntityImages(ImagesDto imagesDto){
//        Images images = new Images();
//        images.setId(imagesDto.getId() != null ? imagesDto.getId() : UUID.randomUUID().toString());
//        images.setLink(imagesDto.getLink());
//        return images;
//    }
    public Images dtoToEntityAddImages(AddImagesDto addImagesDto){
        Images images = new Images();
        images.setId(UUID.randomUUID().toString());
        images.setCreatedAt(GetDateNow.getDateNow());
        images.setTypeImages(addImagesDto.getTypeImages());
        images.setLink(addImagesDto.getLink());
        images.setSize(addImagesDto.getSize());
        images.setTitle(addImagesDto.getTitle());
        return images;
    }
//    public Category dtoToEntityCategory(CategoryDto categoryDto){
//        Category category = new Category();
//        category.setId(categoryDto.getId() != null ? categoryDto.getId() : UUID.randomUUID().toString());
//        category.setCategoryName(categoryDto.getCategoryName());
//        category.setCreatedAt(categoryDto.getCreatedAt() != null ? categoryDto.getCreatedAt() : GetDateNow.getDateNow());
//        category.setModifiedAt(GetDateNow.getDateNow());
//        category.setStatus(categoryDto.getStatus() != null ? categoryDto.getStatus() : "active");
//        return category;
//    }
    public Category dtoToEntityAddCategory(AddCategoryDto addCategoryDto){
        Category category = new Category();
        category.setId(UUID.randomUUID().toString());
        category.setCategoryName(addCategoryDto.getCategoryName());
        category.setCreatedAt(GetDateNow.getDateNow());
        category.setModifiedAt(GetDateNow.getDateNow());
        category.setStatus(addCategoryDto.getStatus());
        return category;
    }
//    public Promotion dtoToEntityPromotion(PromotionDto promotionDto){
//        Promotion promotion = new Promotion();
//        promotion.setId(promotionDto.getId() != null ? promotionDto.getId() : UUID.randomUUID().toString());
//        promotion.setDescription(promotionDto.getDescription());
//        promotion.setPromotionName(promotionDto.getPromotionName());
//        promotion.setPromotionType(promotionDto.getPromotionType());
//        promotion.setDiscountCode(promotionDto.getDiscountCode());
//        promotion.setStatus(promotionDto.getStatus() != null ? promotionDto.getStatus() : "active");
//        promotion.setDiscountValue(promotionDto.getDiscountValue());
//        promotion.setStartDate(promotionDto.getStartDate());
//        promotion.setExpiredDate(promotionDto.getExpiredDate());
//        return promotion;
//    }
    public Promotion dtoToEntityAddPromotion(AddPromotionDto addPromotionDto){
        Promotion promotion = new Promotion();
        promotion.setId(UUID.randomUUID().toString());
        promotion.setDescription(addPromotionDto.getDescription());
        promotion.setPromotionName(addPromotionDto.getPromotionName());
        promotion.setPromotionType(addPromotionDto.getPromotionType());
        promotion.setDiscountCode(addPromotionDto.getDiscountCode());
        promotion.setStatus(addPromotionDto.getStatus());
        promotion.setDiscountValue(addPromotionDto.getDiscountValue());
        promotion.setStartDate(addPromotionDto.getStartDate());
        promotion.setExpiredDate(addPromotionDto.getExpiredDate());
        return promotion;
    }
//    public Orders dtoToEntityOrders(OrdersDto ordersDto){
//        Orders orders = new Orders();
//        orders.setId(ordersDto.getId() != null ? ordersDto.getId() : UUID.randomUUID().toString());
//        orders.setAddressReceiver(ordersDto.getAddressReceiver());
//        orders.setNameReceiver(ordersDto.getNameReceiver());
//        orders.setPhoneReceiver(ordersDto.getPhoneReceiver());
//        orders.setNote(ordersDto.getNote());
//        orders.setPaymentStatus(ordersDto.getPaymentStatus());
//        orders.setStatus(ordersDto.getStatus() != null ? ordersDto.getStatus() : "Chờ đóng gói");
//        orders.setNote(ordersDto.getNote());
//        orders.setShipmentCost(ordersDto.getShipmentCost());
//        orders.setDiscountValue(ordersDto.getDiscountValue());
//        orders.setTotalPay(ordersDto.getTotalPay());
//        orders.setTotalPrice(ordersDto.getTotalPrice());
//        orders.setCreatedAt(ordersDto.getCreatedAt() != null ? ordersDto.getCreatedAt() : GetDateNow.getDateNow());
//        orders.setModifiedAt(GetDateNow.getDateNow());
//        return orders;
//    }
    public Orders dtoToEntityAddOrders(AddOrdersDto addOrdersDto){
        Orders orders = new Orders();
        orders.setId(UUID.randomUUID().toString());
        orders.setAddressReceiver(addOrdersDto.getAddressReceiver());
        orders.setNameReceiver(addOrdersDto.getNameReceiver());
        orders.setPhoneReceiver(addOrdersDto.getPhoneReceiver());
        orders.setNote(addOrdersDto.getNote());
        orders.setPaymentStatus(addOrdersDto.getPaymentStatus());
        orders.setStatus("Chờ đóng gói");
        orders.setShipmentCost(addOrdersDto.getShipmentCost());
        orders.setDiscountValue(addOrdersDto.getDiscountValue());
        orders.setTotalPay(addOrdersDto.getTotalPay());
        orders.setTotalPrice(addOrdersDto.getTotalPrice());
        orders.setCreatedAt(GetDateNow.getDateNow());
        orders.setModifiedAt(GetDateNow.getDateNow());
        return orders;
    }
//    public Item dtoToEntityItem(ItemDto itemDto){
//        Item item = new Item();
//        item.setId(itemDto.getId() != null ? item.getId() : UUID.randomUUID().toString());
//        item.setQuantityInStock(itemDto.getQuantityInStock());
//        item.setSize(ESize.valueOf(itemDto.getSize()));
//        return item;
//    }
    public Item dtoToEntityAddItem(AddItemDto addItemDto){
        Item item = new Item();
        item.setId(UUID.randomUUID().toString());
        item.setQuantityInStock(addItemDto.getQuantityInStock());
        item.setSize(ESize.valueOf(addItemDto.getSize()));
        return item;
    }
}
