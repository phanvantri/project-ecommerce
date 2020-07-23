package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Image_Product;
import com.example.ecommerce.domain.Product_Details;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private Date dateAdd;

    private Date dateUpdate;
    private String detail;
    private String detail_1;
    private String detail_2;
    private String detail_3;

    private String imagephoto;

    private  String description;


    private String category_subID;

    private String status;

    private float price;

    private float pricesale;

    private String color;

    private String size;

    private String url;

    private String url1;

    private String url2;

    private String url3;

    private String productnew;

    private int countSL;
    private Long idproductdetail;






}
