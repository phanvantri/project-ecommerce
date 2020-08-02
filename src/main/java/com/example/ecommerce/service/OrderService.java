package com.example.ecommerce.service;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.model.ChartProduct;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.OrdersItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    OrdersItemRepository ordersItemRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EntityManager entityManager;
    public void sendEmail(Order order, List<Product> products, User user, long fee) {
        String lst="";
        for(Product s:products){
             lst+=""+s.getName()+"   -   "+"Số lương:"+s.getSoluong()+"    -    "+"Giá tiền:"+s.getProduct_details().getPricesale()*s.getSoluong()+" đồng"+"\n";
        }
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Xác nhận thông tin đặt hàng.");
        msg.setText("Xin chào:"+user.getName()+" \nTổng số tiền phải thanh toán là:"+order.getTotalprice()+"đồng."
                +"\nBao gồm: \n"+
                lst+"\n"+"Trong đó, phí ship hàng là: " +fee +"đồng \n"+"Xem đơn hàng tại thông tin cá nhân."+"\n"+"Xin cảm ơn!!!!"
        );
        javaMailSender.send(msg);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    public Page<Order> findAllOrder(int page, int size){
        Pageable pageable= PageRequest.of(page,size);
        return orderRepository.findAll(pageable);
    }
    public List<Order> findOrderByStatus(){
        return orderRepository.findOrderStatus();
    }
    public Order save(Order order){
        return orderRepository.save(order);
    }
    public List<Order> findByOrderOfUser(Long id){
        List<Order> lst = orderRepository.findOrderOfUser(id).stream().limit(5).collect(Collectors.toList());
        return lst;
    }
    public Order findById(Long id){
        return orderRepository.findById(id).get();
    }
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

   public List<Order> countOrderByDate(Date start, Date end){
        return orderRepository.countOrderByDate(start, end);
   }

   public List<ChartProduct> countProductOrderTop(){
        List<ChartProduct> chartProducts = new ArrayList<>();

      Object [] cp = ordersItemRepository.countProductTop();
      for(int i =0;i< 10;i++){
          ChartProduct c = new ChartProduct();
          Object [] tt = (Object[]) cp[i];
          c.setLabel(tt[0].toString());
          c.setY(Long.valueOf(tt[1].toString()));
          chartProducts.add(c);
      }


      return  chartProducts;
   }
   public Order getById(Long id){
        return  orderRepository.findById(id).get();
   }

}
