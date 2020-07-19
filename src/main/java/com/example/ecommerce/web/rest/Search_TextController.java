package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Search_TextController {

    @Autowired
    private OrderService orderService;
    @GetMapping("admin/findorderstatus")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Order>> findAllOrders(@RequestParam int page, @RequestParam int size){

        List<Order> lst=orderService.findOrderByStatus();
        Collections.sort(lst,Collections.reverseOrder(Comparator.comparing(Order::getDateadd)));
        if(!lst.isEmpty()) {
            Pageable pageable;
            if (lst.size() < 3) {
                pageable = PageRequest.of(0, lst.size());
            } else {
                pageable = PageRequest.of(page, size);
            }
            int start = Integer.parseInt(String.valueOf(pageable.getOffset()));
            int end = (start + pageable.getPageSize()) > lst.size() ? lst.size() : (start + pageable.getPageSize());
            Page<Order> pages = new PageImpl<>(lst.subList(start, end), pageable, lst.size());



            return new ResponseEntity<Page<Order>>(pages, HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }
    @GetMapping("/successorder/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> successorder(@PathVariable String id){
        Order order=orderService.findById(Long.parseLong(id));
        order.setBank(true);
        order.setStatus(true);
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/deleteOrder/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteOrder(@PathVariable String id){
        orderService.deleteOrder(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
