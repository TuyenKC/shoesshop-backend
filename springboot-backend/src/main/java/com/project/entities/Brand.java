package com.project.entities;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Brand {
    @Id
    private String id;
    @Column(name = "brand_name")
    private String brandName;
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedAt;
    private String status;
    @OneToMany(mappedBy = "brand")
    private List<Product> productList;
}
