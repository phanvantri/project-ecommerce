package com.example.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="ordersitem")
@Data
public class OrdersItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="idproduct")
    private Product product;

    @Column(name="idorder")
    private Long idorder;

    @Column(name = "dateadd")
    private Date dateAdd;

}
