package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.dto.CategoryDTO;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryDTO>> getCategory(){
        List<CategoryDTO> list=categoryService.getCategory();
        return  new ResponseEntity<List<CategoryDTO>>(list,HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable String id){

        CategoryDTO lst=categoryService.findOneById(Long.parseLong(id));
        return new ResponseEntity<CategoryDTO>(lst,HttpStatus.OK);
    }

    @PostMapping(path="/addcategory")
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category c, BindingResult result){
        if(result.hasErrors()){
            Map<String,String> errorMap=new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
        }

        Category category=categoryService.saveCategory(c);
        return new ResponseEntity<Category>(category,HttpStatus.CREATED);
    }

}
