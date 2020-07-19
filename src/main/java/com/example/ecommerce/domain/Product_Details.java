package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product_details")
@Data
public class Product_Details {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name="price")
    private Float price;

    @Column(name="count")
    private  int count;

    @Column(name="pricesale",nullable = true)
    private float pricesale;

    @OneToOne
    @JoinColumn(name="idproduct")
    @JsonIgnore
    private Product product;

    @Column(name="description")
    private String  description;

    @Column(name="brand")
    private String  brand;

    @Column(name="size")
    private String  size;

    @Column(name="color")
    private String  color;

    @Column(name="status")
    private Boolean  status;

    @Column(name="sumseller")
    private int sumseller;

    @Column(name="new")
    private Boolean New;

    @Column(name="detail")
    private String detail;

    @Column(name="detail_1")
    private String detail_1;

    @Column(name="detail_2")
    private String detail_2;

    @Column(name="detail_3")
    private String detail_3;


    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "product", orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Image_Product> lstImage=new ArrayList<>();


}
