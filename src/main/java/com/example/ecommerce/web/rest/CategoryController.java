package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.dto.CategoryDTO;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ResponseEntity<Page<Category>> getCategory(@RequestParam int page,@RequestParam int size){
        Page<Category> list=categoryService.findAllPage(page,size);
        return  new ResponseEntity<Page<Category>>(list,HttpStatus.OK);
    }
    @GetMapping("/findAllCategory")
    public ResponseEntity<List<Category>> findAllCategory(){
        List<Category> lst=categoryService.getCategory();
        return new ResponseEntity<List<Category>>(lst,HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable String id){

        CategoryDTO lst=categoryService.findOneById(Long.parseLong(id));
        return new ResponseEntity<CategoryDTO>(lst,HttpStatus.OK);
    }

    @PostMapping(path="/addcategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryDTO c, BindingResult result){
        if(result.hasErrors()){
            Map<String,String> errorMap=new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
        }

        Boolean status=categoryService.saveCategory(c);
        if(status==true){
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
    @GetMapping("/deleteCategory/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable String id){

        categoryService.deleteCategory(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
