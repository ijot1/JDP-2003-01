package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDto {
    private Long id;
    private Long cartId;
    private Long userId;
    private Long addressId;
    private final LocalDate orderDate = LocalDate.now();
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
