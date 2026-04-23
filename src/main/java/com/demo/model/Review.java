package com.demo.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
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

    //Asociaciones (ManyToOne) Muchas reseñas a 1 producto,usuario

    @ManyToOne
    private Product product;
    //Constructor vacio y con variables para crear objetos de tipo Review
    public Review(){}

    public Review(Long id, Integer rating, String comment, Boolean userVerified, LocalDateTime createdAt) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.userVerified = userVerified;
        this.createdAt = createdAt;
    }


    //Creamos toString para que veamos en la terminal los datos de la reseña(las variables declaradas)
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", userVerified=" + userVerified +
                ", createdAt=" + createdAt +
                '}';
    }
}
//
