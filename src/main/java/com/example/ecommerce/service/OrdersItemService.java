package com.example.ecommerce.service;

import com.example.ecommerce.domain.OrdersItem;
import com.example.ecommerce.repository.OrdersItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersItemService {
    @Autowired
    private OrdersItemRepository ordersItemRepository;

    public OrdersItem save(OrdersItem ordersItem){
        return ordersItemRepository.save(ordersItem);
    }
    public List<OrdersItem> getAllOrderItems(){
        return ordersItemRepository.findAll();
    }
}
