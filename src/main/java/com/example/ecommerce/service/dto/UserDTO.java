package com.example.ecommerce.service.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String password;
    private  String passwordnew;
    private  String passwordConfirm;
    private  String email;
    private String name;
    private String phone;
    private String role;
    private  String address;
    private String linkimage;
}
