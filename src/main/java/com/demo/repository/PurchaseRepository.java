package com.demo.repository;

import com.demo.model.Purchase;
import com.demo.model.PurchaseLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository  extends JpaRepository<Purchase, Long> {

}
