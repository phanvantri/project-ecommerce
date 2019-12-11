package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.CurrentUser;
import com.example.ecommerce.security.UserPrincipal;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        UserDetails usDetail=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    @PostMapping("user/updateUser")
    @PreAuthorize("hasRole('USER')")
    public User updateUser(@RequestBody User user,@CurrentUser UserPrincipal userPrincipal){

        UserDetails usDetail=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setPassword(usDetail.getPassword());
        return userService.save(user);
       // return user;
    }
}
