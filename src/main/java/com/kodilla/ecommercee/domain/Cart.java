package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "CART")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CART_ID")
    private Long id;

    @NotNull
    @Column(name="CART_DATE")
    private LocalDate cartDate;

    @OneToMany(
            targetEntity = CartItem.class,
            cascade = CascadeType.ALL,
            mappedBy = "cart",
            fetch = FetchType.LAZY
    )
    private List<CartItem> cartItem = new ArrayList<>();
}

