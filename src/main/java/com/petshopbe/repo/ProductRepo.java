package com.petshopbe.repo;

import com.petshopbe.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE :n")
    Page<Product> searchByName(@Param("n") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.productCode LIKE :pC")
    Page<Product> searchByProductCode(@Param("pC") String productCode, Pageable pageable);
}
