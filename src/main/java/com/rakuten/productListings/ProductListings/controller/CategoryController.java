package com.rakuten.productListings.ProductListings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.rakuten.productListings.ProductListings.model.response.Response;
import com.rakuten.productListings.ProductListings.model.response.ResponseFactory;
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
	public ResponseEntity<Response> getcategoryByName(@PathVariable("categoryName") String categoryName) {
		Category category = categoryService.getCategoryByName(categoryName);
		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(category), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@PostMapping("/category")
	@ResponseBody
	public ResponseEntity<Response> addNewCategory(@RequestBody CategoryRequest categoryRequest) {
		try {
			categoryService.saveCategory(categoryRequest);
		} catch (Exception e) {
			return new ResponseEntity<Response>(ResponseFactory.createErrorResponse(e), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@PutMapping("/category")
	@ResponseBody
	public ResponseEntity<Response> updateCategory(@RequestBody CategoryRequest categoryRequest) {
		try {
			categoryService.updateCategory(categoryRequest);
		} catch (Exception e) {
			return new ResponseEntity<Response>(ResponseFactory.createErrorResponse(e), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@DeleteMapping("/category/{categoryName}")
	@ResponseBody
	public ResponseEntity<Response> deleteCategory(@PathVariable("categoryName") String categoryName) {
		try {
			categoryService.deleteByName(categoryName);
		} catch (Exception e) {
			return new ResponseEntity<Response>(ResponseFactory.createErrorResponse(e), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(), HttpStatus.OK);
	}

	@RequestMapping(value = "/category/allChild/{categoryName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> getAllChildCategory(@PathVariable("categoryName") String categoryName)
			throws Exception {
		List<Category> childList = categoryService.getAllChildCategory(categoryName);
		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(childList), HttpStatus.OK);
	}
}
