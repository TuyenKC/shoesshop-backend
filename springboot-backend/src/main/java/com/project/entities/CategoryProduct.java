package com.project.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CategoryProduct {
    @EmbeddedId
    private CategoryProductKey categoryProductKey;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;
}
