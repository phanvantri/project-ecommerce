package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    @NotBlank(message="Tên Danh Mục không để trống!!")
    private String name;

    @Column(name="dateadd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateAdd;

    @Column(name="dateupdate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateUpdate;

    @Column(name="note")
    private String note;
   /* @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "category", orphanRemoval = true)
    private List<Category_Sub> categorySub=new ArrayList<>();*/
}
