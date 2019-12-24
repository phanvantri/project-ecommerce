package com.example.ecommerce.service.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String author;
    private String avatar;
    private String content;
    private String idProduct;

}
