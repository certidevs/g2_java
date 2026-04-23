package com.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
//test
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

    //Constructor vacios
    public Review(){}

    //Creamos Getter y setter de las variables declaradas arriba
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getUserVerified() {
        return userVerified;
    }

    public void setUserVerified(Boolean userVerified) {
        this.userVerified = userVerified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
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
