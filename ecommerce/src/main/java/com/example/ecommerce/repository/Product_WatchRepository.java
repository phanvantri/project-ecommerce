package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Product_Watch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_WatchRepository extends JpaRepository<Product_Watch,Long> {
    @Query("select e.product from Product_Watch e where  e.iduser=?1 order by e.id desc ")
     List<Product> findProductByUser(Long id, Pageable pageable);
}
