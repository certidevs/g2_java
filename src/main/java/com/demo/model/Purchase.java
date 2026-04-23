package com.demo.model;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Entity
@Table(name="purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
// ofertas//descuentos//
    private Long id;

    private Double unitPrice;

    private Integer quantity;

    private Double total;

    private LocalDate purchaseDate;

    public Purchase(Long id, Double unitPrice, Integer quantity, Double total, LocalDate purchaseDate) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
        this.purchaseDate = purchaseDate;
    }

    public Purchase() {

    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", total=" + total +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
