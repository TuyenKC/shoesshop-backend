package com.project.dtos;

import com.project.entities.ProductImages;
import com.project.entities.Users;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
public class ImagesDto {
    private String id;
    private String link;
}
