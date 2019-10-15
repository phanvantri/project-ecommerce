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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "product", orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Image_Product> lstImage=new ArrayList<>();


}
