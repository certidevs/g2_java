package com.demo.repository;

import com.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByRatingGreaterThanEqual(Integer rating);
    //List<Review> findByUserId(Long id);
    List<Review> findByProduct_IdOrderByCreatedAtDesc(Long id);



}
