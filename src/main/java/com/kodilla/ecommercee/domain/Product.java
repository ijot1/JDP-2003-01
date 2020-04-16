package com.kodilla.ecommercee.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="GROUP_ID")
    private Group group;

    @ManyToOne
    @JoinColumn(name="CART_ITEM_ID")
    private CartItem cartItem;

    @Column(name="PRODUCT_NAME")
    private String productName;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="QUANTITY")
    private int quantity;

    @Column(name="PRICE")
    private BigDecimal price;


}
