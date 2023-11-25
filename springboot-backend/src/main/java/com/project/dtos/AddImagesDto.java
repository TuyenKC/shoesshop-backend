package com.project.dtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class AddImagesDto {
    private String title;
    private String link;
    private Double size;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;
    private String typeImages;
    private String usersId;
}
