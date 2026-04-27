package com.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
//quantity * price
    //(discount*price)
    public PurchaseLine( Integer quantity, Purchase purchase, Product product) {
        this.quantity = quantity;
        this.purchase = purchase;
        this.product = product;
    }

    public PurchaseLine() {

    }

    @Override
    public String toString() {
        return "PurchaseLine{ " +
                "quantity=" + quantity +
                ", purchase=" + purchase +
                ", product=" + product +
                '}';
    }
}
