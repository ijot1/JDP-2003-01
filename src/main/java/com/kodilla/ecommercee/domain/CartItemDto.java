package com.kodilla.ecommercee.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CartItemDto {
    private Long id;
    private Long productId;
    private Long cartId;
    private String productName;
    private LocalDate priceDate;
    private Long productCount;
    private LocalDate addedDate;

}
