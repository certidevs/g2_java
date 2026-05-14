package com.demo.repository;

import com.demo.model.PurchaseLine;
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
}