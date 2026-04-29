package com.demo.model;

import com.demo.model.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    private LocalDateTime purchaseDateTime;

    private String discountCode;//Dogo10%

    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;
//@ManyToOne User user

}
