package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.service.dto.CategoryDTO;
import com.example.ecommerce.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    CategoryMapper categoryMapper=new CategoryMapper();

    public   CategoryService(){

    }
    public List<CategoryDTO> getCategory(){

        return categoryMapper.toDto(categoryRepository.findAll());
    }
    public CategoryDTO findOneById(Long id){

        return categoryMapper.toDto(categoryRepository.findOneById(id));

    }

    public Category saveCategory(Category category){
        if(category.getName().equals("") || category.getName()==null){
            category.setName("none");
        }
        return  categoryRepository.save(category);
    }


}
