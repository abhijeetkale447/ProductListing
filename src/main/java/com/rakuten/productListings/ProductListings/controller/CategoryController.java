package com.rakuten.productListings.ProductListings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.productListings.ProductListings.model.Category;
import com.rakuten.productListings.ProductListings.model.request.CategoryRequest;
import com.rakuten.productListings.ProductListings.service.CategoryService;
import com.rakuten.productListings.ProductListings.service.CurrencyConversionService;

@RestController
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CurrencyConversionService currencyConversionService;

	@RequestMapping(value = "/category/{categoryName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Category> getcategoryByName(@PathVariable("categoryName") String categoryName) {
		Category category = categoryService.getCategoryByName(categoryName);
		return new ResponseEntity<Category>(category, HttpStatus.OK);
	}
	
	@PostMapping("/category")
	@ResponseBody
	public ResponseEntity<String> addNewCategory(@RequestBody CategoryRequest categoryRequest) {
		try {
			categoryService.saveCategory(categoryRequest);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@PutMapping("/category")
	@ResponseBody
	public ResponseEntity<String> updateCategory(@RequestBody CategoryRequest categoryRequest) {
		try {
			categoryService.updateCategory(categoryRequest);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/category/{categoryName}")
	@ResponseBody
	public ResponseEntity<String> deleteCategory(@PathVariable("categoryName") String categoryName) {
		try {
			categoryService.deleteByName(categoryName);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
	
	@RequestMapping(value = "/category/allChild/{categoryName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List> getAllChildCategory(@PathVariable("categoryName") String categoryName) throws Exception{
		List<Category> childList = categoryService.getAllChildCategory(categoryName);
		return new ResponseEntity<List>(childList, HttpStatus.OK);
	}
}
