package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

// 1.Añadir comandos para quitar costructores.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @ManyToOne
    //2. si es necesario o quitar los join column.
    private Category category;


    //3. Hacer lombook para cosntruir datos.

//TODO [Reverse Engineering] generate columns from DB
}