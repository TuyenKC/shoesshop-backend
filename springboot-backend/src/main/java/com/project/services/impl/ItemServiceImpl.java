package com.project.services.impl;

import com.project.dtos.*;
import com.project.entities.Item;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.repositories.ItemRepository;
import com.project.services.ItemService;
import com.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final ProductService productService;
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, @Lazy ProductService productService) {
        this.itemRepository = itemRepository;
        this.productService = productService;
    }

    @Override
    public boolean existedByProductSize(String productId, String size) {
        List<Item> items = itemRepository.findAll();
        Item foundItem = null;
        for(Item item: items){
            if(item.getProduct().getId().equals(productId) && item.getSize().name().equals(size))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addItem(String productId, AddItemDto addItemDto) {
        Item item = new DtoToEntityMapper().dtoToEntityAddItem(addItemDto);
        item.setProduct(productService.findById(productId));
        itemRepository.save(item);
    }

    @Override
    public Item findByProductSize(String productId, String size) {
        List<Item> items = itemRepository.findAll();
        Item foundItem = null;
        for(Item item: items){
            if(item.getProduct().getId().equals(productId) && item.getSize().name().equals(size))
            {
                foundItem = item;
                break;
            }
        }
        if(foundItem == null){
            throw new AppException("404", "Vật phẩm không tồn tại");
        }
        return foundItem;
    }

    @Override
    public void updateItem(UpdateItemDto updateItemDto) {
        Item item = findByProductSize(updateItemDto.getProductId(),updateItemDto.getSize());
        item.setQuantityInStock(updateItemDto.getQuantityInStock());
        itemRepository.save(item);
    }

    @Override
    public void updateItemByOrdersItemList(List<AddOrdersItemDto> addOrdersItemDtos) {
        for(AddOrdersItemDto addOrdersItemDto: addOrdersItemDtos){
            Item foundItem = findByProductSize(addOrdersItemDto.getProductId(),addOrdersItemDto.getSize());
            int newQuantity = foundItem.getQuantityInStock() - addOrdersItemDto.getQuantity();
            UpdateItemDto updateItemDto = new UpdateItemDto(foundItem.getProduct().getId(), foundItem.getSize().name(), newQuantity);
            updateItem(updateItemDto);
        }
    }

    @Override
    public void updateItemListByProductId(String productId, List<AddItemDto> itemList) {
        for(AddItemDto addItemDto: itemList){
            if(existedByProductSize(productId,addItemDto.getSize())){
                updateItem(new UpdateItemDto(productId,addItemDto.getSize(),addItemDto.getQuantityInStock()));
            }else{
                addItem(productId, addItemDto);
            }
        }
    }
    @Override
    public void deleteItemByProductId(String productId) {
        List<Item> items = itemRepository.findAll();
        for(Item item: items){
            if(item.getProduct().getId().equals(productId)){
                itemRepository.delete(item);
            }
        }
    }
}
