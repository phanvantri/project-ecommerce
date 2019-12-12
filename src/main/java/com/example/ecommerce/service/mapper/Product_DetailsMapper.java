package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.Product_Details;
import com.example.ecommerce.service.dto.Product_DetailsDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Product_DetailsMapper {
    ModelMapper modelMapper=new ModelMapper();
    public Product_Details toEntity(Product_DetailsDTO dto){
        if(dto==null)
            return null;
        Product_Details p=new Product_Details();
        p=modelMapper.map(dto,Product_Details.class);
        return p;
    }
    public  Product_DetailsDTO toDto(Product_Details entity){
        if(entity==null)
            return null;
        Product_DetailsDTO p=new Product_DetailsDTO();
        p=modelMapper.map(entity,Product_DetailsDTO.class);
        return p;
    }
    public List<Product_Details> toEntity(List<Product_DetailsDTO> lstDto){
        if(lstDto==null)
            return null;
        List<Product_Details> lst= new ArrayList<Product_Details>(lstDto.size());
        for(Product_DetailsDTO s:lstDto){
            lst.add(toEntity(s));
        }
        return lst;
    }
    public List<Product_DetailsDTO> toDto(List<Product_Details> lstEntity){
        if(lstEntity==null)
            return null;
        List<Product_DetailsDTO> lstDto=new ArrayList<Product_DetailsDTO>(lstEntity.size());
        for(Product_Details s:lstEntity){
            lstDto.add(toDto(s));
        }
        return lstDto;
    }
}
