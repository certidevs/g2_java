package com.demo.dto;

import com.demo.model.Like;
import com.demo.model.Purchase;
import com.demo.model.Review;

import java.util.List;

// Información del usuario que no está en la tabla usuario porque
// está en sus asociaciones
// a futuro se puede añadir aquí más info del usuario:
// por ejemplo: favoritos, puntos de fidelización, ...
public record UserStatsDTO(
        long countReviews,
        List<Review> reviews,
        long countPurchase,
        List<Purchase> purchases,
        List<Like> favoriteProduct
) { }