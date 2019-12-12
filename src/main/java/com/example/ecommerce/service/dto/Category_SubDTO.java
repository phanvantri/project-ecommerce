package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Category_SubDTO {
    private Long id;
    private String name;
    private Category category;
    private String linkimage;
   // private List<Product> lstProduct=new ArrayList<>();
}
