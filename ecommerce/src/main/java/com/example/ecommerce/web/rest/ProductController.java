package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.dto.ProductDTO;
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
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProduct")
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<ProductDTO> lstProducts=productService.findAll();
        return new ResponseEntity<List<ProductDTO>>(lstProducts, HttpStatus.OK);
    }
}
