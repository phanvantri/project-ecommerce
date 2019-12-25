package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Image_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Image_ProductRepository extends JpaRepository<Image_Product,Long> {
}
