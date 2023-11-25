package com.project.services.impl;

import com.project.dtos.*;
import com.project.dtos.ProductDto;
import com.project.entities.*;
import com.project.entities.Product;
import com.project.exceptions.AppException;
import com.project.mappers.DtoToEntityMapper;
import com.project.mappers.EntityToDtoMapper;
import com.project.mappers.GetDateNow;
import com.project.repositories.ProductRepository;
import com.project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final ItemService itemService;
    private final CategoryProductService categoryProductService;
    private final ProductImagesService productImagesService;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            BrandService brandService,
            CategoryService categoryService,
            ItemService itemService,
            CategoryProductService categoryProductService,
            ProductImagesService productImagesService
    ) {
        this.productRepository = productRepository;
        this.brandService = brandService;
        this.categoryService = categoryService;
        this.itemService = itemService;
        this.categoryProductService = categoryProductService;
        this.productImagesService = productImagesService;
    }

    @Override
    public boolean existedByProductName(String productName) {
        return productRepository.existsByProductName(productName);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new AppException("Sản phẩm không tồn tại", "404");
        }
        return product.get();
    }

    @Override
    @Transactional
    public ProductDto addProduct(AddProductDto addProductDto) {
        if(productRepository.existsByProductName(addProductDto.getProductName())){
            throw new AppException("Tên sản phẩm đã tồn tại","409");
        }
        Product product = new DtoToEntityMapper().dtoToEntityAddProduct(addProductDto);
        Brand brand = new DtoToEntityMapper().dtoToEntityBrand(brandService.getBrandById(addProductDto.getBrandId()));
        product.setBrand(brand);
        try {
            Product savedProduct = productRepository.save(product);
            itemService.updateItemListByProductId(savedProduct.getId(), addProductDto.getItemList());
            categoryProductService.addCategoryProductByListCategoryName(savedProduct.getId(), addProductDto.getCategoryListName());
            productImagesService.addProductImagesByListImagesId(savedProduct.getId(), addProductDto.getImagesIdList());
            return new EntityToDtoMapper().entityToDtoProduct(savedProduct);
        } catch (AppException e) {
            throw new AppException("Thêm sản phẩm thất bại", "404");
        }

    }

    @Override
    @Transactional
    public ProductDto updateProduct(String id, UpdateProductDto updateProductDto) {
        if (productRepository.findById(id) == null)
            throw new AppException("Sản phẩm không tồn tại", "404");
        Product product = productRepository.findById(id).get();
        if(!updateProductDto.getProductName().equals(product.getProductName()) && productRepository.existsByProductName(updateProductDto.getProductName())){
            throw new AppException("Tên sản phẩm đã tồn tại","409");
        }
        product.setProductName(updateProductDto.getProductName());
        product.setDescription(updateProductDto.getDescription());
        product.setModifiedAt(GetDateNow.getDateNow());
        product.setPrice(updateProductDto.getPrice());
        product.setSalePrice(updateProductDto.getSalePrice());
        product.setStatus(updateProductDto.getStatus());
        product.setTotalSold(updateProductDto.getTotalSold());
        product.setTotalView(updateProductDto.getTotalView());
        Brand brand = brandService.findById(updateProductDto.getBrandId());
        product.setBrand(brand);
        itemService.updateItemListByProductId(id, updateProductDto.getItemList());
        categoryProductService.addCategoryProductByListCategoryName(id, updateProductDto.getCategoryListName());
        productImagesService.deleteProductImagesByProductId(id);
        productImagesService.addProductImagesByListImagesId(id, updateProductDto.getImagesIdList());
        Product savedProduct = productRepository.save(product);
        return new EntityToDtoMapper().entityToDtoProduct(savedProduct);
    }

    @Transactional
    @Override
    public void deleteProduct(String id) {
        itemService.deleteItemByProductId(id);
        categoryProductService.deleteByProductId(id);
        productImagesService.deleteProductImagesByProductId(id);
        if (productRepository.findById(id) == null)
            throw new AppException("Sản phẩm không tồn tại", "404");

        productRepository.deleteById(id);
    }

    @Override
    public ProductDto getProductById(String id) {
        if (!productRepository.findById(id).isPresent())
            throw new AppException("Sản phẩm không tồn tại", "404");
        Product product = productRepository.findById(id).get();
        product.setTotalView(product.getTotalView() + 1);
        productRepository.save(product);
        return new EntityToDtoMapper().entityToDtoProduct(product);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<ProductDto> productDtos = productRepository.findAll().stream()
                .map(product -> new EntityToDtoMapper().entityToDtoProduct(product))
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDto> getAllProductValid() {
        List<ProductDto> productDtos = productRepository.findAll().stream()
                .filter(product -> "Active".equals(product.getStatus()))
                .map(product -> new EntityToDtoMapper().entityToDtoProduct(product))
                .collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDto> getMostViewProductList() {
        List<ProductDto> productDtos = getAllProductValid();
        Collections.sort(productDtos, Comparator.comparing(ProductDto::getTotalView).reversed());
        return productDtos;
    }
    public List<ProductDto> getMostSoldProductList() {
        List<ProductDto> productDtos = getAllProductValid();
        Collections.sort(productDtos, Comparator.comparing(ProductDto::getTotalSold).reversed());
        return productDtos;
    }
    public List<ProductDto> getMostRateProductList() {
        List<ProductDto> productDtos = getAllProductValid();
        Collections.sort(productDtos, Comparator.comparing(ProductDto::getAverageRate).reversed());
        return productDtos;
    }

    @Override
    public void updateStockByOrdersItemList(List<AddOrdersItemDto> addOrdersItemDtoList) {
        for(AddOrdersItemDto addOrdersItemDto: addOrdersItemDtoList){
            Product product = findById(addOrdersItemDto.getProductId());
            product.setTotalSold(product.getTotalSold() + addOrdersItemDto.getQuantity());
            productRepository.save(product);
        }
    }
}
