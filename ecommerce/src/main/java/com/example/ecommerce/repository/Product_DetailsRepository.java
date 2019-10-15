package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Product_Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_DetailsRepository  extends JpaRepository<Product_Details, Long> {
    @Query("select e from Product_Details e where e.product.id=?1")
    Product_Details findByIdProduct(Long id);
}
