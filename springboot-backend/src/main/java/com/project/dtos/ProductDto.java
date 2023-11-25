package com.project.dtos;

import com.project.entities.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
@Data
public class ProductDto {
    private String id;
    private String productName;
    private String description;
    private Long price;
    private Long salePrice;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedAt;
    private Long totalSold;
    private Long totalView;
    private String status;
    private String brandId;
    private String brandName;
    private Double averageRate;
    private List<RateDto> rateList;
    private List<ItemDto> itemList;
    private List<String> categoryListName;
    private List<String> imageIdList;
    private List<ImagesDto> imagesList;
}
