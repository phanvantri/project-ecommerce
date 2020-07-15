package com.example.ecommerce.web;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.OrdersItem;
import com.example.ecommerce.model.ChartProduct;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.OrdersItemService;
import com.example.ecommerce.service.dto.CategoryDTO;
import com.example.ecommerce.service.mapper.CategoryMapper;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin    //phai co de cho phep angular hoac reactjs co the call cac api..neu khong co se khong call dc
// @CrossOrigin(origins = { “http://localhost:3000”, “http://localhost:4200” })
public class TestController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private OrdersItemService ordersItemService;

//    @GetMapping("/test")
//    public ResponseEntity<List<Category>> getCaategory(){
//        List<Category> list=categoryService.getCategory();
//
//        return  new ResponseEntity<List<Category>>(list, HttpStatus.OK);
//    }
        @GetMapping("/test")
        public String getCaategory(){
            return "123";
        }
    @GetMapping("/getorderbydate")
    public ResponseEntity<List<OrdersItem>> getOrderbydate(){
        return new ResponseEntity<List<OrdersItem>>(ordersItemService.getAllOrderItems(),HttpStatus.OK);
    }
    @GetMapping("/getorderbystatus")
    public ResponseEntity<List<OrdersItem>> getOrderbyStatus(@RequestParam int page, @RequestParam int size,
                                                             @RequestParam Date date){
        String query ="select e from OrdersItem ";
        return new ResponseEntity<List<OrdersItem>>(ordersItemService.getAllOrderItems(),HttpStatus.OK);
    }
    @Data
    private class Testne{
            private int id;
            private String playername;
            private  int runscore;
    }

}
