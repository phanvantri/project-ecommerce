package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.service.dto.CategoryDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    ModelMapper modelMapper=new ModelMapper();
    public Category toEntity(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }
        Category category = new Category();
        category=modelMapper.map(dto,Category.class);
        return category;
    }
    public CategoryDTO toDto(Category entity) {
        if ( entity == null ) {
            return null;
        }
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO=modelMapper.map(entity,CategoryDTO.class);
        return categoryDTO;
    }
    public List<Category> toEntity(List<CategoryDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( dtoList.size() );
        for ( CategoryDTO categoryDTODTO : dtoList ) {
            list.add( toEntity( categoryDTODTO ) );
        }

        return list;
    }
    public List<CategoryDTO> toDto(List<Category> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CategoryDTO> list = new ArrayList<CategoryDTO>( entityList.size() );
        for ( Category category : entityList ) {
            list.add( toDto( category ) );
        }

        return list;
    }
}
