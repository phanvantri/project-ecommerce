package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }
    public Category saveCategory(Category category){
        if(category.getNameCategory().equals("") || category.getNameCategory()==null){
            category.setNameCategory("none");
        }
        return  categoryRepository.save(category);
    }
}
