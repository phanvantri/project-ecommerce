package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.service.Category_SubService;
import com.example.ecommerce.service.dto.Category_SubDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Category_SubController {
    @Autowired
    private Category_SubService category_subService;

    @GetMapping("/category_sub")
    public ResponseEntity<List<Category_SubDTO>> findAllByCategory(){
        List<Category_SubDTO> list=category_subService.findAllByCategory(Long.parseLong("3"));
        return new ResponseEntity<List<Category_SubDTO>>(list, HttpStatus.OK);
    }
}
