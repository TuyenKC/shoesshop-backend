package com.project.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductImages {
    @EmbeddedId
    private ProductImagesKey productImagesKey;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @MapsId("imagesId")
    @JoinColumn(name = "images_id")
    private Images images;
}
