package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Review {
    //Creacion de variables (las declaramos vacias)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    private String comment;

    private Boolean userVerified;

    private LocalDateTime createdAt;

    //Añadido campo adicional
    private String title;

    //Asociaciones (ManyToOne) Muchas reseñas a 1 producto,usuario

    @ManyToOne
    private Product product;

    }
