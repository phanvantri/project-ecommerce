package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.Image_Product;
import com.example.ecommerce.service.dto.Image_ProductDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Image_ProductMapper {
    ModelMapper modelMapper=new ModelMapper();
    public Image_Product toEntity(Image_ProductDTO dto){
        if(dto==null)
            return  null;
        Image_Product i=new Image_Product();
        i=modelMapper.map(dto,Image_Product.class);
        return i;
    }
    public Image_ProductDTO toDto(Image_Product entity){
        if(entity==null)
            return null;
        Image_ProductDTO i=new Image_ProductDTO();
        i=modelMapper.map(entity,Image_ProductDTO.class);
        return i;
    }
    public List<Image_Product> toEntity(List<Image_ProductDTO> lstDto){
        if(lstDto==null)
            return null;
        List<Image_Product> i=new ArrayList<>(lstDto.size());
        for(Image_ProductDTO s:lstDto){
            i.add(toEntity(s));
        }
        return i;
    }
    public List<Image_ProductDTO> toDto(List<Image_Product> lstEntity){
        if(lstEntity==null)
            return null;
        List<Image_ProductDTO> i=new ArrayList<>(lstEntity.size());
        for(Image_Product s:lstEntity){
            i.add(toDto(s));
        }
        return i;
    }
}
