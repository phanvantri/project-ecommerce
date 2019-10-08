package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="product")
@Data
public class Product {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @Column(name="description")
    private String description;

    @Column(name="brand")
    private String brand;

    @ManyToOne(fetch = FetchType.LAZY) //mặc định là FetchType.EAGER
    @JoinColumn(name="idstore")
    @JsonIgnore
    private Store store;

    @Column(name="size")
    private String size;

    @Column(name="color")
    private String color;

    @ManyToOne(fetch = FetchType.LAZY) //FetchType.EAGER lay tat ca doi tuong lien quan (vd: Lay duoc object danh muc)// FetchType.EAGER khong lay cac doi tuong lien quan
    @JoinColumn(name="idcategorysub")
    @JsonIgnore
    private Category_Sub category_sub;
}
