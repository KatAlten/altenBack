package com.shop.alten.repository;

import com.shop.alten.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.id = ?1")
    Optional<Product> findProductById(Long Id);
}
