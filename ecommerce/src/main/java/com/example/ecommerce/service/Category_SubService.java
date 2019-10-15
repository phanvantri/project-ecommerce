package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.repository.Category_SubRepository;
import com.example.ecommerce.service.dto.Category_SubDTO;
import com.example.ecommerce.service.mapper.Category_SubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Category_SubService {
    @Autowired
    private Category_SubRepository category_subRepository;

    Category_SubMapper category_subMapper=new Category_SubMapper();

    public List<Category_SubDTO> findAllByCategory(Long idCategory){
        return category_subMapper.toDto(category_subRepository.findAllByCategoryById(idCategory));
    }

    public Category_SubDTO findOneById(Long id){
        return category_subMapper.toDto(category_subRepository.findOneById(id));
    }

    public void save(Category_SubDTO category_subDTO){

    }
}
