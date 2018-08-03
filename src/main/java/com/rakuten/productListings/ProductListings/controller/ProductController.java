package com.rakuten.productListings.ProductListings.controller;

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

import com.rakuten.productListings.ProductListings.model.Product;
import com.rakuten.productListings.ProductListings.model.request.ProductRequest;
import com.rakuten.productListings.ProductListings.service.ProductService;

@RestController
// @RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/product/{productName}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Product> getProductByName(@PathVariable("productName") String productName) {
		Product prod = productService.getProductByName(productName);
		return new ResponseEntity<Product>(prod, HttpStatus.OK);
	}

	@PostMapping("/product")
	@ResponseBody
	public ResponseEntity<String> addNewProduct(@RequestBody ProductRequest productPojo) {

		System.out.println(productPojo);
		try {
			productService.saveProduct(productPojo);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@PutMapping("/product")
	@ResponseBody
	public ResponseEntity<String> updateExistingProduct(@RequestBody ProductRequest productPojo) {

		try {
			productService.updateProduct(productPojo);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping("/product/{productName}")
	@ResponseBody
	public ResponseEntity<String> deleteExistingProduct(@PathVariable("productName") String productName) {
		try {
			productService.deleteByName(productName);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
