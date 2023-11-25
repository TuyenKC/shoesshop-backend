package com.project.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    private String id;
    @Column(name = "product_name")
    private String productName;
    private String description;
    private Long price;
    @Column(name = "sale_price")
    private Long salePrice;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedAt;
    @Column(name = "total_sold")
    private Long totalSold;
    @Column(name = "total_view")
    private Long totalView;
    private String status;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @OneToMany(mappedBy = "product")
    private List<Rate> rateList;
    @OneToMany(mappedBy = "product")
    private List<Item> items;
    @OneToMany(mappedBy = "product")
    private List<CategoryProduct> categoryProducts;
    @OneToMany(mappedBy = "product")
    private List<ProductImages> productImages;

}
