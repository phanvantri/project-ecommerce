package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.OrdersItem;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.model.ProductModel;
import com.example.ecommerce.security.CurrentUser;
import com.example.ecommerce.security.EncyptData;
import com.example.ecommerce.security.UserPrincipal;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.OrdersItemService;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.service.dto.InforOrder;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin

public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrdersItemService ordersItemService;
    @Autowired
    private UserService userService;

    @GetMapping("/getallorder")
    public ResponseEntity<List<Order>> getall(){
        List<Order> lst=orderService.findAll();
        return new ResponseEntity<List<Order>>(lst, HttpStatus.OK);
    }
    @GetMapping("/orderofuser")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getOrderByUser(@CurrentUser UserPrincipal userPrincipal){
        List<Order> lst=orderService.findByOrderOfUser(userPrincipal.getId());
        return new ResponseEntity<List<Order>>(lst,HttpStatus.OK);
    }
    @GetMapping("admin/findallorder")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Order>> findAllOrder(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(orderService.findAllOrder(page,size),HttpStatus.OK);
    }

    @PostMapping("/addOrder")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String addOrder(@RequestBody List<Product> product, @CurrentUser UserPrincipal userPrincipal){
        User user=userService.findById(userPrincipal.getId());
        Date date = new Date();
        Order order=new Order();
        long totalprice=0;
        order.setName("Order:"+user.getName());
        order.setUser(user);
        order.setDateadd(date);
        order.setStatus(false);
        long fee = product.get(0).getFee();

        for(Product s: product){
            totalprice+=s.getProduct_details().getPricesale()*s.getSoluong();
        }
        order.setTotalprice(Long.parseLong(String.valueOf(totalprice))+fee);
      Order objOrder=orderService.save(order);
        InforOrder inforOrder = new InforOrder();
        inforOrder.setIdOrder(objOrder.getId());
        inforOrder.setIdUser(user.getId());
        inforOrder.setTotalprice(Integer.parseInt(String.valueOf(totalprice+fee)));
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString ="";
        try {
            jsonInString = mapper.writeValueAsString(inforOrder);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encrypt =  EncyptData.encrypt(jsonInString);
        objOrder.setQrCode(encrypt);
        orderService.save(objOrder);
      //set orders item
        for(Product s: product){
            OrdersItem obj=new OrdersItem();
            obj.setProduct(s);
            obj.setIdorder(objOrder.getId());
            obj.setDateAdd( new Date());
            ordersItemService.save(obj);
        }
        orderService.sendEmail(objOrder,product,user,fee);

        return  encrypt;
    }

}
