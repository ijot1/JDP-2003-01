package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private Long groupId;
    private String description;
    private int quantity;
    private BigDecimal price;
}

