package com.demo.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;


@Entity
@Table(name="purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
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
