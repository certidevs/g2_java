package com.demo.repository;
import com.demo.model.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
    @Query("""
        SELECT p FROM Product p
        WHERE p.activo = true
        AND (:price IS NULL OR p.price <= :price)
        ORDER BY p.price asc
        """)
    List<Product> findActivoMinMax(@Param("price") Double price);
    @Query("""
        SELECT p FROM Product p
        WHERE p.activo = true
        AND (:prices IS NULL OR p.price <= :prices)
        ORDER BY p.price DESC
        """)
    List<Product> findActivoMaxMin(@Param("prices") Double prices);
    @Query("""
        SELECT p FROM Product p
        WHERE p.activo = true
        AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
        """)
    List<Product> findActivoFiltering(@Param("name") String name);

    List<Product> findByCategory_Id(Long id);


    //boolean isPresent();
}