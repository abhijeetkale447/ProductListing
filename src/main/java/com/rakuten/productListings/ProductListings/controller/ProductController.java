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
import com.rakuten.productListings.ProductListings.model.Product;
import com.rakuten.productListings.ProductListings.model.request.ProductRequest;
import com.rakuten.productListings.ProductListings.model.response.Response;
import com.rakuten.productListings.ProductListings.model.response.ResponseFactory;
import com.rakuten.productListings.ProductListings.service.CategoryService;
import com.rakuten.productListings.ProductListings.service.ProductService;

@RestController
// @RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "/product/{productName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> getProductByName(@PathVariable("productName") String productName) {
		Product prod = productService.getProductByName(productName);
		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(prod), HttpStatus.OK);
	}

	@RequestMapping(value = "/product/category/{categoryName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Response> getProductUnderCategory(@PathVariable("categoryName") String categoryName) {

		List<Category> childCategoryList;
		try {
			childCategoryList = categoryService.getAllChildCategory(categoryName);
		} catch (Exception e) {
			return new ResponseEntity<Response>(ResponseFactory.createErrorResponse(e), HttpStatus.BAD_REQUEST);
		}
		List<Product> childProducts = productService.getProductsUnderCategory(childCategoryList);

		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(childProducts), HttpStatus.OK);

	}

	@PostMapping("/product")
	@ResponseBody
	public ResponseEntity<Response> addNewProduct(@RequestBody ProductRequest productPojo) {

		System.out.println(productPojo);
		try {
			productService.saveProduct(productPojo);
		} catch (Exception e) {
			return new ResponseEntity<Response>(ResponseFactory.createErrorResponse(e), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(), HttpStatus.CREATED);
	}

	@PutMapping("/product")
	@ResponseBody
	public ResponseEntity<Response> updateExistingProduct(@RequestBody ProductRequest productPojo) {

		try {
			productService.updateProduct(productPojo);
		} catch (Exception e) {
			return new ResponseEntity<Response>(ResponseFactory.createErrorResponse(e), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(), HttpStatus.OK);
	}

	@DeleteMapping("/product/{productName}")
	@ResponseBody
	public ResponseEntity<Response> deleteExistingProduct(@PathVariable("productName") String productName) {
		try {
			productService.deleteByName(productName);
		} catch (Exception e) {
			return new ResponseEntity<Response>(ResponseFactory.createErrorResponse(e), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Response>(ResponseFactory.createValidResponse(), HttpStatus.OK);
	}

}
