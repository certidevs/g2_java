package com.demo.repository;

import com.demo.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByUser_IdAndProductIsNotNull(Long id);
    Optional<Like> findByUser_IdAndProduct_Id(Long userId, Long productId);
    // Usados por UserService para las estadísticas del perfil
    long countByUser_Id(Long id);
    List<Like> findByUser_Id(Long id);

    @Query("""
    select f.product.id 
    from Like f 
    where f.user.id = :userId 
    and f.product IS NOT NULL
""")
    Set<Long> findProductsIdsByUserid(@Param("userId") Long id);

}