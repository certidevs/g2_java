package com.demo.repository;

import com.demo.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseRepository  extends JpaRepository<Purchase, Long> {
Optional<Purchase> findById(Long Id);
}
