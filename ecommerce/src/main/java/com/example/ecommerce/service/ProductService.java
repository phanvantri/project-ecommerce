package com.example.ecommerce.service;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private ProductMapper productMapper=new ProductMapper();

    public List<ProductDTO> findAll(){
        return productMapper.toDto(productRepository.findAll());
    }

}
