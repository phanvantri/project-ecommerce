package com.example.ecommerce.service;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
