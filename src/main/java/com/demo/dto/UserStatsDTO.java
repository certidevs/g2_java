package com.demo.dto;

import com.demo.model.Review;

import java.util.List;

//Aquí se pone la información del usuario que no está en la tabla usuario
//Porque está en sus asociaciones
public record UserStatsDTO(
        long countReview,
        List<Review> reviews
) { }
