package com.example.ecommerce.service;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(Order order, List<Product> products, User user) {
        String lst="";
        for(Product s:products){
             lst+="Tên sản phẩm:"+s.getName()+"-"+"Giá tiền:"+s.getProduct_details().getPricesale()+" đồng"+"\n";
        }
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Xác nhận thông tin đặt hàng.");
        msg.setText("Xin chào:"+user.getName()+" \nTổng số tiền phải thanh toán là:"+order.getTotalprice()+"đồng."
                +"\nBao gồm: \n"+
                lst+"\n"+"Xem đơn hàng tại:http://localhost:3000/profile-page ."+"\n"+"Xin cảm ơn!!!!"
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
    public Order save(Order order){
        return orderRepository.save(order);
    }
    public List<Order> findByOrderOfUser(Long id){
        return orderRepository.findOrderOfUser(id);
    }
}
