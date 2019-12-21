package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Product_Watch;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.payload.ApiResponse;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.CurrentUser;
import com.example.ecommerce.security.UserPrincipal;
import com.example.ecommerce.service.Product_WatchService;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.dto.UserDTO;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private Product_WatchService product_watchService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/findalluser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<User>> findAllUser(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>( userService.findAllUser(page,size),HttpStatus.OK);
    }

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
    @PostMapping("user/changepassword")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user, @CurrentUser UserPrincipal userPrincipal){

        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        UserDetails usDetail=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean check=passencoder.matches(user.getPassword(), usDetail.getPassword());
        if(check==true){
            Optional<User> numLog=userRepository.findById(userPrincipal.getId());
            User user1=numLog.get();
            user1.setPassword(passwordEncoder.encode(user.getPasswordnew()));
            userService.save(user1);
             return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
