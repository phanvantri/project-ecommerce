package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category_sub")
@Data
public class Category_Sub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="idcategory")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private Category category;

    @Column(name="linkimage")
    private String linkimage;

    @OneToMany(
            mappedBy = "category_sub",
            fetch = FetchType.LAZY, //FetchType.EAGER lấy lun thông tin product lien quan//FetchType.LAZY không lấy các thèn product lien quan
            cascade = CascadeType.REMOVE, //khi xóa Danh muc này thì tất cả product thuoc danh muc nay sẽ bị xóa hêt
            orphanRemoval = true  // khi remove 1 đối tượng product khỏi lstProduct thì nó sẽ bị xóa khỏi database
    )
    private List<Product> lstProduct=new ArrayList<>();

    public Category_Sub() {
    }
}
