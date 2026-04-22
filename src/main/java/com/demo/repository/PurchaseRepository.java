package com.demo.repository;

import com.demo.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository  extends JpaRepository<Purchase, Long> {
}
