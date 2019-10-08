package com.example.ecommerce.service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private Date dateAdd;
    private Date dateUpdate;
    private String note;
}
