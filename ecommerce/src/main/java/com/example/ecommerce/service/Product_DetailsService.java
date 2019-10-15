package com.example.ecommerce.service;

import com.example.ecommerce.domain.Product_Details;
import com.example.ecommerce.repository.Product_DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Product_DetailsService {
    @Autowired
    private Product_DetailsRepository product_detailsRepository;

    public Product_Details findById(Long id){
        return product_detailsRepository.findByIdProduct(id);
    }
}
