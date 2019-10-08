package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Store;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private String code;

    private String description;

    private String brand;

  //  private Store store;

    private String size;

    private String color;

   // private Category_Sub category_sub;
}
