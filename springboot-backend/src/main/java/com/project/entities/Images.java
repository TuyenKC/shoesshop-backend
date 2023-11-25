package com.project.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Images {
    @Id
    private String id;
    private String title;
    private String link;
    private Double size;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    @Column(name = "type_images")
    private String typeImages;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users users;
    @OneToMany(mappedBy = "images")
    private List<ProductImages> productImages;
}
