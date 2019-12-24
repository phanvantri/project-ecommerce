package com.example.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="iduser")
    private User user;

    @ManyToOne
    @JoinColumn(name="idproduct")
    private Product product;

    @Column(name="content")
    private String content;

    @Column(name="datecomment")
    private Date datecomment;


}
