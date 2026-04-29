package com.demo.model;

import jakarta.persistence.*;
import lombok.*;
// /images/productos/creatina.jpg

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//1.usar to String
@Entity
@Table(name = "product")
public class Product {
    @Id
    //2. Cambiar de auto a identity.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String shortDescription;
    //Para descripcion larga vendria bien poner mas caracteres, osea un lenght que sea mas grande que el default de 255
    @Column(length = 1000)
    private String longDescription;
    //tambien seria buena idea poner que los precios no sean null?
    //@Column(nullable = false)
    private Double price;
    //igual que el stock?
    //@Column(nullable = false)
    private Integer stock;
    //4?.Quiza añadir imagenes.
    @ManyToOne
    //3.Excluir los may to one
    private Category category;


//TODO [Reverse Engineering] generate columns from DB
}