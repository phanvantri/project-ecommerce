package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.*;
import com.example.ecommerce.repository.BankUserRepository;
import com.example.ecommerce.repository.CardRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.CurrentUser;
import com.example.ecommerce.security.EncyptData;
import com.example.ecommerce.security.UserPrincipal;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.dto.InforOrder;
import com.example.ecommerce.util.RamdomString;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/app")
public class AppController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BankUserRepository bankUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardRepository cardRepository;
    @GetMapping("/gerateCode/{money}")
    public Card generateCode(@PathVariable Long money) throws Exception {
        Card card = new Card();
        card.setActive(true);
        card.setMoney(money);

        String code = RamdomString.getSaltString();
        Optional<Card> c = cardRepository.findById(code);
        while (c.isPresent()){
            code = RamdomString.getSaltString();
            c = cardRepository.findById(code);
        }
        card.setCode(code);
        cardRepository.save(card);
        return card;

    }

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(List<String> product , User user, Long money, Long moneyCl) {
        String lst="";
        for(String s:product){
            lst+=s + "\n";
        }
        Date date = new Date();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setSubject("Thanh toán thành công đơn hàng.");
        msg.setText("Xin chào:"+user.getName()+" \nĐơn hàng bạn đã được thanh toán thành công với số tiền:"+money+"đồng, vào lúc: "+ date+
                "\nBao gồm: \n"+
                lst+"\n"+ "Số tiền còn lại trong tài khoản:" +moneyCl +"đồng."+ "\n"+"Xin cảm ơn!!!!"
        );
        javaMailSender.send(msg);
    }
    @GetMapping("/sendmoney")
    public boolean sendMoney(@RequestParam String code, @CurrentUser UserPrincipal userPrincipal){
        try {
            Card card = cardRepository.getOne(code);
            if (card!= null && card.getActive() == true) {
                BankUser bankUser = bankUserRepository.getBankUserByUserBank(userPrincipal.getId());
                Long money = bankUser.getMoney();
                bankUser.setMoney(money + card.getMoney());
                bankUserRepository.save(bankUser);
                cardRepository.delete(card);
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;


    }
    @GetMapping ("/bank")
    public boolean getBank(@RequestParam Long idOrder,@CurrentUser UserPrincipal userPrincipal){
        BankUser bankUser = bankUserRepository.getBankUserByUserBank(userPrincipal.getId());
        User user = userRepository.findById(userPrincipal.getId()).get();
        Order order = orderService.getById(idOrder);
        if(order.getUser().getId() != userPrincipal.getId()){
            return false;
        }
        if(order.isBank() == true){
            return false;
        }
        if(order.getTotalprice() <bankUser.getMoney()){
            Long money = order.getTotalprice();
            Long moneyCL = bankUser.getMoney() - money;
            order.setBank(true);
            orderService.save(order);
            bankUser.setMoney(bankUser.getMoney() - order.getTotalprice());
            bankUserRepository.save(bankUser);
            List<String> product = new ArrayList<>();
            for(OrdersItem a : order.getLstOrder()){
                product.add(a.getProduct().getName() + ":" + a.getProduct().getProduct_details().getPricesale());
            }
            sendEmail(product,user,money, moneyCL);
            return true;
        }
        return false;
    }
    @GetMapping("/infomation")
    public Information getInformation(@CurrentUser UserPrincipal userPrincipal){
        BankUser bankUser = bankUserRepository.getBankUserByUserBank(userPrincipal.getId());
        User user = userRepository.findById(userPrincipal.getId()).get();
        Information information = new Information();
        information.setUsername(user.getName());
        information.setEmail(user.getEmail());
        information.setAddress(user.getAddress());
        information.setPhone(user.getPhone());
        information.setMoney(bankUser.getMoney());
        information.setImagePath(user.getImageUrl());
        return information;

    }

    @GetMapping("/getInfoOrder")
    public ModelQrcode getOrder(String code){
        String value =EncyptData.decrypt(code);
        ObjectMapper mapper = new ObjectMapper();
        InforOrder emp2 = null;
        try {
            emp2 = mapper.readValue(value, InforOrder.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Order o1 = orderService.getById(emp2.getIdOrder());
        ModelQrcode m = new ModelQrcode();
        m.setIdOrder(emp2.getIdOrder());
        m.setUsername(o1.getName());
        m.setTimeOrder(o1.getDateadd());
        List<String> product = new ArrayList<>();
        for(OrdersItem a : o1.getLstOrder()){
            product.add(a.getProduct().getName() + " Số tiền: " + a.getProduct().getProduct_details().getPricesale() +" đồng");
        }
        m.setProduct(product);
        m.setTotalPrice(o1.getTotalprice());
        m.setBank(o1.isBank());
        m.setAddress(o1.getUser().getAddress());
        return m;
    }

    @Data
    private class ModelQrcode{
        private Long idOrder;
        private String username;
        private Long totalPrice;
        private boolean bank;
        private String address;
        private List<String> product;
        private Date timeOrder;

    }
    @Data
    private class Information{
        private String username;
        private Long money;
        private String email;
        private String address;
        private String phone;
        private String imagePath;

    }


}
