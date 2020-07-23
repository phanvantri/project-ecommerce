package com.example.ecommerce.service;

import com.example.ecommerce.domain.Category_Sub;
import com.example.ecommerce.domain.Image_Product;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Product_Details;
import com.example.ecommerce.repository.Category_SubRepository;
import com.example.ecommerce.repository.Image_ProductRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.Product_DetailsRepository;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.mapper.ProductMapper;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Product_DetailsRepository product_detailsRepository;

    @Autowired
    private Category_SubRepository category_subRepository;

    @Autowired
    private Image_ProductRepository image_productRepository;

    private ProductMapper productMapper=new ProductMapper();
    public Product findById(Long id){
        Optional<Product> user= productRepository.findById(id);
        return  user.get();
    }

    public List<Product> findByProductNew(){

        return  productRepository.findByProductNew();
    }
    public List<ProductDTO> findAllByProductSumseller(){
        return productMapper.toDto(productRepository.findAllByProductSumseller());
    }
    public Page<Product> findAllPage(int page,int size){
        Pageable pageable= PageRequest.of(page,size, Sort.by("dateAdd").descending());//0 là trang, 2 la so phan tu
        Page<Product> lst=productRepository.findAll(pageable);
        return lst;
    }

    public Page<Product> findProductOfCategory(int idCategory,int page,int size){
        Pageable pageable= PageRequest.of(page,size);//0 là trang, 2 la so phan tu
        Page<Product> lst=productRepository.findAll(pageable);

        return lst;
    }
    public  boolean updateProduct(Product product){
        productRepository.save(product);
        return true;
    }
    public boolean save(ProductDTO productDTO){
       try{
           Date date=new Date();
           Category_Sub category_sub=category_subRepository.findOneById(Long.parseLong(productDTO.getCategory_subID()));
           Product product=new Product();
           product.setName(productDTO.getName());
           product.setCategory_sub(category_sub);
           product.setDateAdd(date);
           product.setDateUpdate(date);
           product.setImagephoto(productDTO.getUrl());
           Product product1=productRepository.save(product);
           Product_Details product_details=new Product_Details();
           product_details.setId(product1.getId());
           product_details.setPrice(productDTO.getPrice());
           product_details.setPricesale(productDTO.getPricesale());
           product_details.setCount(productDTO.getCountSL());
           product_details.setProduct(product1);
           product_details.setDescription(product_details.getDescription());
           product_details.setColor(productDTO.getColor());
           product_details.setDetail(productDTO.getDetail());
           product_details.setDetail_1(productDTO.getDetail_1());
           product_details.setDetail_2(productDTO.getDetail_2());
           product_details.setDetail_3(productDTO.getDetail_3());
           if(productDTO.getProductnew().equals("1")){
               product_details.setNew(true);
           }
           else {
               product_details.setNew(false);
           }
           product_details.setSize(productDTO.getSize());
          Product_Details product_details1= product_detailsRepository.save(product_details);
           for(int i=0;i<3;i++){
               Image_Product image_product=new Image_Product();
               if(i==0){
                   image_product.setName(productDTO.getUrl1());
               }
               if(i==1){
                   image_product.setName(productDTO.getUrl2());
               }
               if(i==2){
                   image_product.setName(productDTO.getUrl3());
               }
               image_product.setProduct(product_details1);
               image_productRepository.save(image_product);

           }
           return true;
       }
       catch (Exception e){
           return false;
       }


    }

    public void deleteProduct(Long id){ ;
        productRepository.deleteById(id);

    }
    public Optional<Product> getdetailsProduct(Long id){
        Optional<Product> product=productRepository.findById(id);
        return product;
    }

    public List<Product> searchProduct(String name){
        return productRepository.searchProduct(name);
    }

}
