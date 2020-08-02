package com.example.ecommerce.web.rest;

import com.example.ecommerce.domain.AuthProvider;
import com.example.ecommerce.domain.BankUser;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.exception.BadRequestException;
import com.example.ecommerce.payload.ApiResponse;
import com.example.ecommerce.payload.AuthResponse;
import com.example.ecommerce.payload.LoginRequest;
import com.example.ecommerce.payload.SignUpRequest;
import com.example.ecommerce.repository.BankUserRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.TokenProvider;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;
    @Autowired
    private BankUserRepository bankUserRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setImageUrl("https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png");
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole("ROLE_USER");
        User result = userRepository.saveAndFlush(user);
        BankUser bankUser = new BankUser();
        bankUser.setMoney((long)0);
        bankUser.setUser(result);
        bankUserRepository.save(bankUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }
    @PostMapping("/resetpassword")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user){
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        Optional<User> numLog=userRepository.findByEmail(user.getEmail());
        User user1=numLog.get();
        user1.setPassword(passwordEncoder.encode(saltStr));
       // try{
            userService.sendEmail(user1,saltStr);
            userService.save(user1);
            return new ResponseEntity<>(HttpStatus.OK);
       // }
//        catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }

    }

}
