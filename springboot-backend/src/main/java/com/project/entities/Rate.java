package com.project.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Rate {
    @Id
    private String id;
    private int star;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @Column(name = "modified_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date modifiedAt;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
