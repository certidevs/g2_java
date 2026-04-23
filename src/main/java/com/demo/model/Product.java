package com.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String shortDescription;
    //Para descripcion larga vendria bien poner mas caracteres, osea un lenght que sea mas grande que el default de 255
    //@Column(length = 1000)
    private String longDescription;
    //tambien seria buena idea poner que los precios no sean null?
    //@Column(nullable = false)
    private Double price;
    //igual que el stock?
    //@Column(nullable = false)
    private Integer stock;

    public Product(String name, String shortDescription, String longDescription, Double price, Integer stock) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
    }
//TODO [Reverse Engineering] generate columns from DB
}