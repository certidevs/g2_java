package com.demo.repository;
import com.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
//Repository del producto

public interface ProductRepository extends JpaRepository<Product, Long> {
}
