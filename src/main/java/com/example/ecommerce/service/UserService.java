package com.example.ecommerce.service;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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

}
