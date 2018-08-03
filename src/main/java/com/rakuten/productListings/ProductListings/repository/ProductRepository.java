package com.rakuten.productListings.ProductListings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakuten.productListings.ProductListings.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
   
	Product findByName(String name); 
}
