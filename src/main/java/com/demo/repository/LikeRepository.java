package com.demo.repository;

import com.demo.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser_IdAndProductIsNotNull(Long id);
    Optional<Like> findByUser_IdAndProductIsNotNull(Long userId, Long productId);

}