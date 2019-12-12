package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) //FetchType.EAGER lay tat ca doi tuong lien quan (vd: Lay duoc object danh muc)// FetchType.EAGER khong lay cac doi tuong lien quan
    @JoinColumn(name="idcategorysub")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Category_Sub category_sub;

    @Column(name="imagephoto")
    private String imagephoto;

    @Column(name="dateadd")
    private Date dateAdd;

    @Column(name="dateupdate")
    private Date dateUpdate;

    @OneToOne(cascade = CascadeType.ALL,
            mappedBy = "product", orphanRemoval = true,fetch = FetchType.EAGER)
    private Product_Details product_details ;


/*

 @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "product", orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Image_Product> lstImage=new ArrayList<>();
   */

}
