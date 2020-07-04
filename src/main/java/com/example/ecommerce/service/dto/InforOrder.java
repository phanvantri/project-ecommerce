package com.example.ecommerce.service.dto;

import lombok.Data;

@Data
public class InforOrder {
    private Long idOrder;
    private Long idUser;
    private int totalprice;
}
