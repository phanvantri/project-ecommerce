package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.repository.Category_SubRepository;
import com.example.ecommerce.service.dto.Category_SubDTO;
import com.example.ecommerce.service.mapper.Category_SubMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Category_SubService {
    @Autowired
    private Category_SubRepository category_subRepository;

    Category_SubMapper category_subMapper=new Category_SubMapper();

    public List<Category_Sub> findAllByCategory(Long idCategory){
        return category_subRepository.findAllByCategoryById(idCategory);
    }
    public Page<Product> findProductOfCategory(Long idCategory,int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        Page<Product> lst=category_subRepository.findProductOfCategory(idCategory,pageable);
        return lst;
    }
    public List<Product> findProductOfCategoryTest(Long idCategory){
        List<Product> lst=category_subRepository.findProductOfCategoryTest(idCategory);
        return lst;
    }
    public List<Category_Sub> findCategory_SubByCategory(Long idCategory){
        List<Category_Sub> lst=category_subRepository.findCategory_SubByCategory(idCategory);
        return lst;
    }


    public Category_SubDTO findOneById(Long id){
        return category_subMapper.toDto(category_subRepository.findOneById(id));
    }

    public void save(Category_SubDTO category_subDTO){

    }
}
