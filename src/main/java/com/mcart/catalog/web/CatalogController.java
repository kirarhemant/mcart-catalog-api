package com.mcart.catalog.web;

import com.mcart.catalog.domain.Product;
import com.mcart.catalog.repo.ProductRepo;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private final ProductRepo repo;

    public CatalogController(ProductRepo repo) {
        this.repo = repo;
    }

    /**
     * Product Listing Page (PLP) - paged by categoryId.
     * Example:
     *   GET /api/catalog/products?categoryId=3&page=0&size=20
     */
    @GetMapping("/products")
    public Page<Product> list(@RequestParam Long categoryId,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
        return repo.findByCategoryId(categoryId, pageable);
    }

    /**
     * Product Detail Page (PDP) - by SKU.
     * Example:
     *   GET /api/catalog/products/SKU-00001
     */
    @GetMapping("/products/{sku}")
    public Optional<Product> bySku(@PathVariable String sku) {
        return repo.findBySku(sku);
    }
}