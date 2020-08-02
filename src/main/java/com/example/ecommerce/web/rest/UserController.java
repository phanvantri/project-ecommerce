package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.BankUser;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Product_Watch;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.payload.ApiResponse;
import com.example.ecommerce.repository.BankUserRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.CurrentUser;
import com.example.ecommerce.security.UserPrincipal;
import com.example.ecommerce.service.Product_WatchService;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.service.dto.ProductDTO;
import com.example.ecommerce.service.dto.UserDTO;
import com.example.ecommerce.service.mapper.UserMapper;
import com.sun.net.httpserver.Authenticator;
import lombok.Data;
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

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.security.SignatureException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BankUserRepository bankUserRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private Product_WatchService product_watchService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/findalluser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<User>> findAllUser(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(userService.findAllUser(page, size), HttpStatus.OK);
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        UserDetails usDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("/getcurrentuser")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Current getCurrent(@CurrentUser UserPrincipal userPrincipal) {
        UserDetails usDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
        Current current = new Current();
        current.setId(user.getId());
        current.setUsername(user.getName());
        return current;
    }

    @GetMapping("/user/getproductwatch")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Product>> getproductwatch(@CurrentUser UserPrincipal userPrincipal) {
        List<Product> lst = product_watchService.findProductByUser(userPrincipal.getId());
        if (lst.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(lst, HttpStatus.OK);
    }

    @PostMapping("user/changepassword")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user, @CurrentUser UserPrincipal userPrincipal) {

        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        UserDetails usDetail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean check = passencoder.matches(user.getPassword(), usDetail.getPassword());
        if (check == true) {
            Optional<User> numLog = userRepository.findById(userPrincipal.getId());
            User user1 = numLog.get();
            user1.setPassword(passwordEncoder.encode(user.getPasswordnew()));
            userService.save(user1);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {

        userService.deleteUser(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("user/updateUser")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> UpdateUser(@RequestBody User user, @CurrentUser UserPrincipal userPrincipal) {
        Optional<User> entity = userRepository.findById(user.getId());
        if (entity.isPresent()) {
            User en =entity.get();
            en.setPhone(user.getPhone());
            en.setAddress(user.getAddress());
            return new ResponseEntity<>(userService.save(en), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("user/adduser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> AddUser(@RequestBody UserDTO userDTO, @CurrentUser UserPrincipal userPrincipal) {

        UserMapper userMapper = new UserMapper();
        Date date = new Date();
        PasswordEncoder passencoder = new BCryptPasswordEncoder();
        User user = userMapper.toEntity(userDTO);
        user.setDate(date);
        user.setFullname(userDTO.getName());
        user.setPassword(passencoder.encode(userDTO.getPassword()));
        user.setImageUrl(userDTO.getLinkimage());
        try {
            User userEntity = userRepository.saveAndFlush(user);
            BankUser bankUser = new BankUser();
            bankUser.setMoney((long)0);
            bankUser.setUser(userEntity);
            bankUserRepository.save(bankUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("admin/user/findbyid")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findUserbyId(@RequestParam Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PostMapping("admin/user/updateUser")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> UpdateUser(@RequestBody User userDTO) {

        Date date = new Date();
        User user = userRepository.findById(userDTO.getId()).get();
        user.setName(userDTO.getName());
        user.setRole(userDTO.getRole());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setDate(date);
        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @Data
    private class Current {
        private Long id;
        private String username;
    }


}
