package com.project.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    private String id;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedAt;
    private String status;
    @OneToMany(mappedBy = "category")
    private List<CategoryProduct> categoryProducts;
}
