package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Product_Details;
import com.example.ecommerce.service.Product_DetailsService;
import com.example.ecommerce.service.dto.Product_DetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Product_DetailsController {
    @Autowired
    private Product_DetailsService product_detailsService;

    @GetMapping("/getproductdetails")
    public ResponseEntity<Product_DetailsDTO> findByIdProduct(){
        Product_DetailsDTO p= product_detailsService.findByIdProduct(Long.parseLong("1"));
        return new ResponseEntity<Product_DetailsDTO>(p, HttpStatus.CREATED);
    }
}
