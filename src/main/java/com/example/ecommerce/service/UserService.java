package com.example.ecommerce.service;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public User save(User user){
        return userRepository.save(user);
    }
    public User findById(Long id){
        Optional<User> user= userRepository.findById(id);
        return  user.get();
    }
    public Page<User> findAllUser(int page, int size){
        Pageable pageable= PageRequest.of(page,size);//0 là trang, 2 la so phan tu
        Page<User> lst=userRepository.findAll(pageable);
        return lst;
    }
    public void sendEmail(User user, String password) {
        String lst="";

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());

        msg.setSubject("Reset mật khẩu đăng nhập.");
        msg.setText("Xin chào:"+user.getName()+" \n Mật khẩu đăng nhập tạm thời:"+password+"."+ "\n"
             + "Vui lòng đổi lại mật khẩu cho bạn" +"\n"+"Xin cảm ơn!!!"
        );
        msg.setFrom("myenglish20191998@gmail.com");
        javaMailSender.send(msg);

    }
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }



}
