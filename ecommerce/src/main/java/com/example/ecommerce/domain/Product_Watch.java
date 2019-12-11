package com.example.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product_watch")
@Data
public class Product_Watch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToOne
    @JoinColumn(name="idproduct")
    private Product product;

    @Column(name="iduser")
    private Long iduser;
}
