package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Product_Details;
import com.example.ecommerce.service.Category_SubService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.Product_DetailsService;
import com.example.ecommerce.service.Product_WatchService;
import com.example.ecommerce.service.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private Product_DetailsService product_detailsService;
    @Autowired
    private Category_SubService category_subService;
    @Autowired
    private EntityManager entityManager;


    @GetMapping("/search")
    public ResponseEntity<Page<Product>> searchProduct(@RequestParam() String keyword,@RequestParam int page,@RequestParam int size){

        //  List<Product> lstProducts=productService.findByProductNew();
        List<Product> lstProducts=productService.searchProduct(keyword);
        Pageable pageable;
        if(lstProducts.size()<3){
             pageable= PageRequest.of(0,lstProducts.size());
        }
        else {
             pageable = PageRequest.of(page, size);
        }
        int start = Integer.parseInt(String.valueOf(pageable.getOffset()));
        int end = (start + pageable.getPageSize()) > lstProducts.size() ? lstProducts.size() : (start + pageable.getPageSize());
        Page<Product> pageslstProduct = new PageImpl<>(lstProducts.subList(start, end), pageable, lstProducts.size());
        if(lstProducts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Page<Product>>(pageslstProduct, HttpStatus.OK);

    }
  /*  @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword,@RequestParam int page,@RequestParam int size){

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class)
                .get();

        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .onFields("name")
                .matching(keyword)
                .createQuery();

        org.hibernate.search.jpa.FullTextQuery jpaQuery
                = fullTextEntityManager.createFullTextQuery(query, Product.class);

         List<Product> a=jpaQuery.getResultList();
       return new ResponseEntity<>(a,HttpStatus.OK);
    }*/

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
    public ResponseEntity<Page<Product>> getproductnew(@RequestParam int page, @RequestParam int size){

        List<Product> lstProducts=productService.findByProductNew();
       /* if (lst.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(lst,HttpStatus.OK);*/
        Pageable pageable= PageRequest.of(page,size);
        int start = Integer.parseInt(String.valueOf(pageable.getOffset()));
        int end = (start + pageable.getPageSize()) > lstProducts.size() ? lstProducts.size() : (start + pageable.getPageSize());
        Page<Product> pageslstProduct = new PageImpl<>(lstProducts.subList(start, end), pageable, lstProducts.size());
        if(lstProducts.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Page<Product>>(pageslstProduct, HttpStatus.OK);
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
    @GetMapping("/deleteProduct/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        productService.deleteProduct(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/addproductbyAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addproductbyAdmin(@RequestBody ProductDTO productDTO){


        boolean status=productService.save(productDTO);
        if(status==true){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
    @GetMapping("admin/product/findbyid")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>findProductbyId(@RequestParam Long id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }
    @PostMapping("admin/user/updateProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> UpdateUser(@RequestBody ProductDTO productDTO){

        Product product=productService.findById(productDTO.getId());
        Product_Details product_details=product_detailsService.findbyId(productDTO.getIdproductdetail());
        product.setName(productDTO.getName());
        product_details.setDescription(productDTO.getDescription());
        product_details.setStatus(Boolean.valueOf(productDTO.getStatus()));
        product_details.setNew(Boolean.valueOf(productDTO.getStatus()));
        product_details.setColor(productDTO.getColor());
        product_details.setPrice(productDTO.getPrice());
        product_details.setPricesale(productDTO.getPricesale());
        product.setProduct_details(product_details);
        try {
            productService.updateProduct(product);
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}
