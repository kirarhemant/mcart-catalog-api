package com.mcart.catalog.repo;

import com.mcart.catalog.domain.Product;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
}