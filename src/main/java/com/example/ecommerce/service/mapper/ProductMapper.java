package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.dto.ProductDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    ModelMapper modelMapper=new ModelMapper();
    public Product toEntity(ProductDTO productDTO){
        if(productDTO==null)
            return null;
        Product product=new Product();
        product=modelMapper.map(productDTO,Product.class);
        return product;
    }
    public ProductDTO toDto(Product product){
        if(product==null)
            return null;
        ProductDTO productDTO = new ProductDTO();
        productDTO=modelMapper.map(product,ProductDTO.class);
        return productDTO;
    }
    public List<Product> toEntity(List<ProductDTO> dtoList){
        if(dtoList==null)
            return null;
        List<Product> lst=new ArrayList<Product>(dtoList.size());
        for(ProductDTO productDTO: dtoList){
            lst.add(toEntity(productDTO));
        }
        return lst;
    }

    public List<ProductDTO> toDto(List<Product> entityList){
        if(entityList ==null)
            return null;
        List<ProductDTO> lst=new ArrayList<ProductDTO>(entityList.size());
        for(Product s:entityList){
            lst.add(toDto(s));
        }
        return lst;
    }
}
