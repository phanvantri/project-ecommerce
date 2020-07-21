package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="dateadd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateadd;

    @ManyToOne
    @JoinColumn(name="iduser")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "idorder")
    private List<OrdersItem> lstOrder=new ArrayList<>();

    @Column(name="totalprice")
    private Long totalprice;

    @Column(name="status")
    private Boolean status;

    @Column(name="qrcode")
    private String qrCode;

    @Column(name="bank")
    private boolean  bank;

}
