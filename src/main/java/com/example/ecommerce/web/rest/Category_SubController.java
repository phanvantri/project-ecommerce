package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.Category_SubService;
import com.example.ecommerce.service.dto.Category_SubDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Category_SubController {
    @Autowired
    private Category_SubService category_subService;

    @GetMapping("/category_sub")
    public ResponseEntity<List<Product>> findAllByCategory(){
       // List<Product> list=category_subService.findAllByCategory(Long.parseLong("5"));
        return null;
    }
    @PostMapping("/findCategorySubOfCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<?>findCategorySubOfCategory(@RequestBody Category category)
    {
        List<Category_Sub> lst=category_subService.findCategory_SubByCategory(category.getId());
        return  new ResponseEntity<>(lst,HttpStatus.OK);
    }
}
