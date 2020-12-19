package com.codetest.springbootapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductOrderDto {

    private Long productId;

    private String productName;

    private String productDescription;

    private BigDecimal price;

    private int quantity;
}
