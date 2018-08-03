package com.rakuten.productListings.ProductListings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rakuten.productListings.ProductListings.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public Category findByName(String name);
	
	
	@Query("SELECT C from Category C WHERE C.parentCategory.categoryId = ?1")
	public List<Category> getChildCategory(Integer id);
}
