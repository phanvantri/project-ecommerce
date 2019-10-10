package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="fullname",nullable = false)
    private String fullname;

    @Email
    @Column(name="email",nullable = false)
    private String email;

    @Column(name="emailVerified",nullable = false)
    private  Boolean emailVerified;

    @Column(name="phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name="linkimage")
    private String linkimage;


    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;




}

