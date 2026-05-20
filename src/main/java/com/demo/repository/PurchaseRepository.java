package com.demo.repository;

import com.demo.model.Purchase;
import com.demo.model.enums.PurchaseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PurchaseRepository  extends JpaRepository<Purchase, Long> {
    Optional<Purchase> findById(Long Id);
    Optional<Purchase> findFirstByStatus(PurchaseStatus status);

}
