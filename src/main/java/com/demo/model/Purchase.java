package com.demo.model;

import com.demo.model.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//Crear purchaseLine para unir pedidos a uno solo
//ManyToOne product
//ManyToOne purchase
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
// ofertas//descuentos//
private Long id;

    private Double totalPrice;

    private LocalDateTime purchaseDate;


    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    // pago
    // private PaymentMethod method; // CASH, CARD
    private String cardOwner; // titular
    private String cardNumber; // XXXXYYYYZZZZWWWW
    private String cardExpirationDate; // MM-YY


    @ToString.Exclude
    @ManyToOne
    private User user;

}
