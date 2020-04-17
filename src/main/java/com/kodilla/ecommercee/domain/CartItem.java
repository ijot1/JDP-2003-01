package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "CART_ITEM")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "CART_ITEM_ID")
    private Long id;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRICE_DATE")
    private LocalDate priceDate;

    @Column(name = "PRODUCT_COUNT")
    private Long productCount;

    @Column(name = "ADDED_DATE")
    private LocalDate addedDate;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;


    @OneToMany(
            targetEntity = Product.class,
            cascade = CascadeType.ALL,
            mappedBy = "cartItem",
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();
}

