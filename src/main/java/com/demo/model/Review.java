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

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    //Añadido campo adicional
    private String title;

    //Añadido para el botón de desactivar

    private Boolean active = true;

    //Asociaciones (ManyToOne) Muchas reseñas a 1 producto,usuario
    @ToString.Exclude
    @ManyToOne
    private Product product;

  @ToString.Exclude
   @ManyToOne
   private User user;
//
 }
