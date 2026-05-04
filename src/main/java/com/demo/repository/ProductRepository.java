package com.demo.repository;
import com.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

//Repository del producto
public interface ProductRepository extends JpaRepository<Product, Long> {
    //Precio menor a mayor
    List<Product> findProductsByPriceOrderByPriceAsc(Double price);
    //Precio Mayor a menor
    List<Product> findProductsByPriceOrderByPriceDesc(Double price);

    List<Product> findByActivoTrue();
    Optional<Product> findByIdAndActivoTrue(Long id);


    //boolean isPresent();
}