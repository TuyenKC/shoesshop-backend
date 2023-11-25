package com.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class RevenueDateDto {
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private Double revenue;
}
