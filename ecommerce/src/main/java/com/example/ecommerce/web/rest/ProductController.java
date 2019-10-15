package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.Category_SubService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getproductnew")
    public ResponseEntity<List<ProductDTO>> getproductnew(){

        List<ProductDTO> lst=productService.findByProductNew();
        if (lst.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDTO>>(lst,HttpStatus.OK);
    }
    @PostMapping("/addproduct")
    public ResponseEntity<ProductDTO> addproduct(@RequestBody ProductDTO productDTO){

        productService.save(productDTO);
       return new ResponseEntity<ProductDTO>(productDTO,HttpStatus.CREATED);

    }
}
