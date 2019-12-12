package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Product_Watch;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.CurrentUser;
import com.example.ecommerce.security.UserPrincipal;
import com.example.ecommerce.service.Product_WatchService;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private Product_WatchService product_watchService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        UserDetails usDetail=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    @GetMapping("/user/getproductwatch")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Product>> getproductwatch(@CurrentUser UserPrincipal userPrincipal){
        List<Product> lst=product_watchService.findProductByUser(userPrincipal.getId());
        if (lst.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(lst,HttpStatus.OK);
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
