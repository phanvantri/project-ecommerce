package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.repository.Category_SubRepository;
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

    @Autowired
    private Category_SubRepository category_subRepository;

    private ProductMapper productMapper=new ProductMapper();

    public List<ProductDTO> findAll(){
        return productMapper.toDto(productRepository.findAll());
    }

    public List<ProductDTO> findByProductNew(){
        List<Product> l=productRepository.findByProductNew();
        return  productMapper.toDto(productRepository.findByProductNew());
    }
    public void save(ProductDTO productDTO){
        Category_Sub category_sub=category_subRepository.findOneById(Long.parseLong(productDTO.getCategory_subID()));
        Product product=productMapper.toEntity(productDTO);
        product.setCategory_sub(category_sub);
        productRepository.save(product);
    }

    public void delete(ProductDTO productDTO){
        Product product=productMapper.toEntity(productDTO);
        productRepository.delete(product);

    }

}
