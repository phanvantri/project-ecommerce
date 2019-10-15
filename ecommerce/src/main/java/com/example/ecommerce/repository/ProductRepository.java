package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //lay san pham moi nhat
    @Query("select e from Product e order by e.id desc ")
    public List<Product> findByProductNew();
}
