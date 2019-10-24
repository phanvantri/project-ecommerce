package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.service.dto.OrderDTO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    ModelMapper modelMapper=new ModelMapper();
    public OrderDTO toDto(Order entity){
        if(entity==null)
            return null;
        OrderDTO o=new OrderDTO();
        o=modelMapper.map(entity,OrderDTO.class);
        return o;
    }

    public Order toEntity(OrderDTO dto){
        if(dto==null)
            return null;
        Order o=new Order();
        o=modelMapper.map(dto,Order.class);
        return o;
    }
    public List<Order> toEntity(List<OrderDTO>lstDto){
        if(lstDto==null)
            return null;
        List<Order> o=new ArrayList<>(lstDto.size());
        for(OrderDTO s:lstDto){
            o.add(toEntity(s));
        }
        return o;
    }
     public List<OrderDTO> toDto(List<Order> lstEntity){
        if(lstEntity==null)
            return null;
        List<OrderDTO> o=new ArrayList<>(lstEntity.size());
        for(Order s:lstEntity){
            o.add(toDto(s));
        }
        return o;
     }
}
