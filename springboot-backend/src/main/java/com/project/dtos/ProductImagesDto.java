package com.project.dtos;

import com.project.entities.Images;
import com.project.entities.Product;
import com.project.entities.ProductImagesKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImagesDto {
    private String productId;
    private String imagesId;
    private String url;

    public ProductImagesDto(String productId, String imagesId) {
        this.productId = productId;
        this.imagesId = imagesId;
    }
}
