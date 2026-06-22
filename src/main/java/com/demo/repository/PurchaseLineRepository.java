package com.demo.repository;

import com.demo.model.Product;
import com.demo.model.PurchaseLine;
import com.demo.model.enums.PurchaseStatus;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PurchaseLineRepository extends JpaRepository<PurchaseLine, Long> {


    List<PurchaseLine> findByPurchaseId(Long purchaseId);
    @Query("""
            SELECT SUM(pl.quantity * pl.product.price)
            FROM PurchaseLine pl where pl.purchase.id =?1
            """)
    Double calculateTotalPrice(Long purchaseId);


    Optional<PurchaseLine> findByPurchaseIdAndProductId(Long purchaseId, Long productId);

    @Query("SELECT SUM(pl.quantity) FROM PurchaseLine pl WHERE pl.purchase.user.id = ?1 AND pl.purchase.status = ?2")
    Integer countCartItems(Long userId, PurchaseStatus status);

}