package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.service.dto.CategoryDTO;
import com.example.ecommerce.service.dto.Category_SubDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Category_SubMapper {
    ModelMapper modelMapper=new ModelMapper();

    public Category_Sub toEntity(Category_SubDTO dto){
        if(dto ==null)
            return null;
        Category_Sub category_sub=new Category_Sub();
        category_sub=modelMapper.map(dto,Category_Sub.class);
        return category_sub;
    }

    public Category_SubDTO toDto(Category_Sub entity){

        if(entity == null)
            return null;
        Category_SubDTO category_subDTO=new Category_SubDTO();
        category_subDTO=modelMapper.map(entity,Category_SubDTO.class);
        return category_subDTO;
    }

    public List<Category_Sub> toEntity(List<Category_SubDTO> dtoList){
        if(dtoList==null)
            return null;
        List<Category_Sub> list = new ArrayList<Category_Sub>( dtoList.size() );
        for ( Category_SubDTO category_subDTODTO : dtoList ) {
            list.add( toEntity( category_subDTODTO ) );
        }
        return list;
    }
    public List<Category_SubDTO> toDto(List<Category_Sub> entityList) {
        if ( entityList == null ) {
            return null;
        }
        List<Category_SubDTO> list = new ArrayList<Category_SubDTO>( entityList.size() );
        for ( Category_Sub category : entityList ) {
            list.add( toDto( category ) );
        }
        return list;
    }
}
