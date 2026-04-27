package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class PurchaseLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

   private Long id;

   private Integer quantity;

@ManyToOne
    private Purchase purchase;
@ManyToOne
    private Product product;



    public PurchaseLine(int i, Purchase p1, Product producto) {
    this.quantity = i;
    this.purchase = p1;
    this.product = producto;
    }
//quantity * price
    //(discount*price)

}
