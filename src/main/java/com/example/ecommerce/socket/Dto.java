package com.example.ecommerce.socket;

import lombok.Data;

@Data
public class Dto {
    private String message;

    public Dto(String s) {
        this.message = s;
    }
}
