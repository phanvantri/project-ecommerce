package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
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



    @Email
    @Column(name="email",nullable = false)
    private String email;

     @Column(name="emailverified")
    private  Boolean emailVerified;

    @JsonIgnore
    @Column(name="password")
    private String password;

    @Column(name="linkimage")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name="provider")
    private AuthProvider provider;

    @Column(name="providerid")
    private String providerId;

    @Column(name="fullname",nullable = false)
    private String fullname;

    @Column(name="phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name="role")
    private String role;
    @Column(name="date")
    private Date date;




}

