package com.example.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Data
@Entity
@Table(name="bank_user")
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idbank_user")
    private Long id;

    @Column(name="money")
    private Long money;

    @ManyToOne(fetch = FetchType.LAZY) //FetchType.EAGER lay tat ca doi tuong lien quan (vd: Lay duoc object danh muc)// FetchType.EAGER khong lay cac doi tuong lien quan
    @JoinColumn(name="iduser")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnore
    private User user;

}
