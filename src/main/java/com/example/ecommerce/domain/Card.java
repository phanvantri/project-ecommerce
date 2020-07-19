package com.example.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="card")
@Data
public class Card {
    @Id
    @Column(name="code")
    private String code;

    @Column(name="active")
    private Boolean active;

    @Column(name="money")
    private Long money;


}
