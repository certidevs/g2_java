package com.demo.repository;

import com.demo.model.Purchase;
import com.demo.model.enums.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository  extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUser_IdOrderByPurchaseDateDesc(Long id);
    long countByUser_Id(Long id);

    @Query("""
SELECT SUM(p.totalPrice) from Purchase p where p.user.id = :userId
""") // Traer la suma de purchase totalPrice solo de los pedidos del user que yo te diga


    double calculateTotalMoneySpentByUserId(Long userId);

    Optional<Purchase> findFirstByStatus(PurchaseStatus purchaseStatus);
    Optional<Purchase> findFirstByStatusAndUserId(PurchaseStatus purchaseStatus, Long userId);
}

// traer todos los pedidos de un restaurante
// findByRestaurantId
