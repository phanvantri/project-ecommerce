package com.example.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="search_text")
@Data
public class Search_Text {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;
}
