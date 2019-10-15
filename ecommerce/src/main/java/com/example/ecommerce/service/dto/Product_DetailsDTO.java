package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.Image_Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Product_DetailsDTO {
    private Long id;
    private Float price;
    private int count;
    private Float pricesale;
    private String  description;
    private String  brand;
    private String  size;
    private String  color;
    private Boolean  status;
    private List<Image_Product> lstImage=new ArrayList<>();
}
