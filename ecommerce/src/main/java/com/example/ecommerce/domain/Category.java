package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name="category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcategory")
    private int id;

    @Column(name="namecategory")
    @NotBlank(message="Tên Danh Mục không để trống!!")
    private String nameCategory;

    @Column(name="dateadd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateAdd;

    @Column(name="dateupdate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateUpdate;

    @Column(name="note")
    private String note;
}
