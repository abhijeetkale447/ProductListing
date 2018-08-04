package com.rakuten.productListings.ProductListings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rakuten.productListings.ProductListings.model.Category;
import com.rakuten.productListings.ProductListings.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
   
	Product findByName(String name); 
	
	@Query("SELECT P from Product P WHERE P.category.categoryId IN ?1")
	public List<Product> getChildProducts(List<Integer> idList);
	
	@Query("SELECT P from Product P WHERE P.category.categoryId = ?1")
	List<Product> findByCategoryName(Integer categoryId);
}
