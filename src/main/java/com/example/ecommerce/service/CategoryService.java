package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.Category_SubRepository;
import com.example.ecommerce.service.dto.CategoryDTO;
import com.example.ecommerce.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Category_SubRepository category_subRepository;
    CategoryMapper categoryMapper=new CategoryMapper();

    public   CategoryService(){

    }
    public List<Category> getCategory(){

        return categoryRepository.findAll();
    }
    public CategoryDTO findOneById(Long id){

        return categoryMapper.toDto(categoryRepository.findOneById(id));

    }
    public Page<Category> findAllPage(int page, int size){
        Pageable pageable= PageRequest.of(page,size);//0 là trang, 2 la so phan tu
        Page<Category> lst=categoryRepository.findAll(pageable);
        return lst;
    }

    public boolean saveCategory(CategoryDTO categoryDTO){
        if(categoryDTO.getName().equals("") || categoryDTO.getName()==null){
            categoryDTO.setName("none");
        }
        try {
            Category category = new Category();
            Date date=new Date();
            category.setName(categoryDTO.getName());
            category.setDateAdd(date);
            category.setDateUpdate(date);
            category.setLinkimage(categoryDTO.getUrl());
            categoryRepository.save(category);
            for (int i = 0; i < 3; i++) {
                Category_Sub category_sub = new Category_Sub();
                if (i == 0) {
                    category_sub.setName(categoryDTO.getDanhmuc1());
                    category_sub.setLinkimage(categoryDTO.getLinkimage1());
                }
                if (i == 1) {
                    category_sub.setName(categoryDTO.getDanhmuc2());
                    category_sub.setLinkimage(categoryDTO.getLinkimage2());
                }
                if (i == 2) {
                    category_sub.setName(categoryDTO.getDanhmuc3());
                    category_sub.setLinkimage(categoryDTO.getLinkimage3());
                }
                category_sub.setCategory(category);
                category_subRepository.save(category_sub);
            }


            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public void deleteCategory(Long id ){
        categoryRepository.deleteById(id);
    }

    public  void updateCategory(Category category){
        categoryRepository.save(category);
    }




}
