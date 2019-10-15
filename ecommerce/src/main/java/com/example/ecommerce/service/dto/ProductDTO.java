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

    private String imagephoto;

    //private Category_Sub category_sub;

    private String category_subID;

    private Product_Details product_details ;


}
