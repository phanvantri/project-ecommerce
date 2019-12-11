package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.service.Category_SubService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private Category_SubService category_subService;


    @GetMapping("/getProductOfCategory")
    public ResponseEntity<Page<Product>> getProductOfCategory(@RequestParam int idCategory,@RequestParam int page,@RequestParam int size){
       Long id=Long.parseLong(String.valueOf(idCategory));
        List<Product> lstProducts=category_subService.findProductOfCategoryTest(id);
        Pageable pageable= PageRequest.of(page,size);
        int start = Integer.parseInt(String.valueOf(pageable.getOffset()));
        int end = (start + pageable.getPageSize()) > lstProducts.size() ? lstProducts.size() : (start + pageable.getPageSize());
        Page<Product> pageslstProduct = new PageImpl<>(lstProducts.subList(start, end), pageable, lstProducts.size());
        if(lstProducts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Page<Product>>(pageslstProduct, HttpStatus.OK);
    }


    @GetMapping("/getallProduct")
    public ResponseEntity<Page<Product>> findAll(@RequestParam int page, @RequestParam int size ){

        Page<Product> lstProducts=productService.findAllPage(page,size);

        if(lstProducts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Page<Product>>(lstProducts, HttpStatus.OK);
    }
    @GetMapping("/getproductnew")
    public ResponseEntity<List<ProductDTO>> getproductnew(){

        List<ProductDTO> lst=productService.findByProductNew();
        if (lst.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDTO>>(lst,HttpStatus.OK);
    }
    @GetMapping("/getproductsumseller")
    public ResponseEntity<List<ProductDTO>> getproductsumseller(){
        List<ProductDTO> lst=productService.findAllByProductSumseller();
        if(lst.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lst,HttpStatus.OK);
    }
    @PostMapping("/addproduct")
    public ResponseEntity<ProductDTO> addproduct(@RequestBody ProductDTO productDTO){

        productService.save(productDTO);
       return new ResponseEntity<ProductDTO>(productDTO,HttpStatus.CREATED);
    }
    @GetMapping("/productdetail")
    public ResponseEntity<Optional<Product>> getproductdetails(@RequestParam String id){
        return new ResponseEntity<Optional<Product>>(productService.getdetailsProduct(Long.parseLong(id)),HttpStatus.OK);
    }
}
