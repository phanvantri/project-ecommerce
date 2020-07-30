package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    //lay san pham moi nhat
    @Query("select e from Product e where e.product_details.New=true order by e.dateUpdate desc ")
    public List<Product> findByProductNew();

    @Query(value = "select e from Product e  order by  e.product_details.sumseller")
    public List<Product> findAllByProductSumseller();

    @Query(value ="select e from Product e" )
    public Page<Product> finAll(Pageable pageable);

    @Query(value = "select e from Product e where e.name like %:name%")
    List<Product> searchProduct(@Param("name") String name);






}
