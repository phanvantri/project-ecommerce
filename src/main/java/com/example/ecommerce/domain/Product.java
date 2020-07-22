package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="product")
@Data
public class Product {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Long soluong;
    @Transient
    private Long fee;

    @Column(name="name")
    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    private String name;



    @Column(name="imagephoto")
    private String imagephoto;

    @Column(name="dateadd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateAdd;

    @Column(name="dateupdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateUpdate;

    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "product", orphanRemoval = true,fetch = FetchType.EAGER)
    private Product_Details product_details ;
    @ManyToOne(fetch = FetchType.LAZY) //FetchType.EAGER lay tat ca doi tuong lien quan (vd: Lay duoc object danh muc)// FetchType.EAGER khong lay cac doi tuong lien quan
    @JoinColumn(name="idcategorysub")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Category_Sub category_sub;


}
