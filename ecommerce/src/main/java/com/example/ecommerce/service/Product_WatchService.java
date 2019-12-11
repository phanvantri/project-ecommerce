package com.example.ecommerce.service;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Product_Watch;
import com.example.ecommerce.repository.Product_WatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Product_WatchService {
    @Autowired
    private Product_WatchRepository product_watchRepository;
    public List<Product> findProductByUser(Long id){
        //lay 4 cai xem gan day
        return product_watchRepository.findProductByUser(id,new PageRequest(0,4));
    }
}
