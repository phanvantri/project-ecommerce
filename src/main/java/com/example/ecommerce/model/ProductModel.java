package com.example.ecommerce.model;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Product_Details;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;
import java.util.Date;

@Data
public class ProductModel {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="name")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String name;

    private String imagephoto;

    private Date dateAdd;
    
    private Date dateUpdate;

    private Product_Details product_details ;

    private Category_Sub category_sub;
}
