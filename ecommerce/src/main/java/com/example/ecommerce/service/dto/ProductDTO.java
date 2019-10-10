package com.example.ecommerce.service.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private String code;

    private String description;

    private String brand;


    private String size;

    private String color;

   // private Category_Sub category_sub;
}
