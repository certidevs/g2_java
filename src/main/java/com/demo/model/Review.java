package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="Reseñas")
public class Review {
    @Id
    private Long id;

    private Integer rating;

    private String comment;

    private LocalDateTime createdAt;


}
