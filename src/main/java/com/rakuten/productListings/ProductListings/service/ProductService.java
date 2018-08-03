package com.rakuten.productListings.ProductListings.service;

import java.util.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.productListings.ProductListings.model.Category;
import com.rakuten.productListings.ProductListings.model.Product;
import com.rakuten.productListings.ProductListings.model.request.ProductRequest;
import com.rakuten.productListings.ProductListings.repository.CategoryRepository;
import com.rakuten.productListings.ProductListings.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CurrencyConversionService conversionService;
	
	public Product getProductByName(String name)
	{
		return productRepository.findByName(name);
	}
	
	public Product saveProduct(ProductRequest prod) throws Exception
	{
		Product product = getProductByName(prod.getName());
		if(product != null)
		{
			throw new Exception("Bad Request: product with same name already exists");
		}
		product = new Product();
		
		product = validateAndPopulateProductValues(product, prod);
		product = productRepository.save(product);
		return product;
	}
	
	public Product updateProduct(ProductRequest prod) throws Exception 
	{
		Product product = productRepository.findByName(prod.getName());
		if(product == null)
		{
			throw new Exception("Bad Request: no product with this name.");
		}
		if(prod.checkIfProductsSame(product)) {
			throw new Exception("Bad Request: No change needed. Same product already exists.");
		}
		product = validateAndPopulateProductValues(product,prod);
		product = productRepository.save(product);
		return product;
	}
	
	public Product validateAndPopulateProductValues(Product product, ProductRequest prod) throws Exception
	{
		Currency currencyFrom;
		product.setName(prod.getName());
		product.setDescription(prod.getDescription());
		Category category = categoryRepository.findByName(prod.getCategory());
		if(category == null)
		{
			throw new Exception("Bad Request: Invalid category");
		}
		try {
			currencyFrom= Currency.getInstance(prod.getCurrency());
		}
		catch(IllegalArgumentException e)
		{
			throw new Exception("Bad Request: Invalid currency code");
		}
		product.setPrice(conversionService.convertToEur(currencyFrom, prod.getPrice()));
		product.setCategory(category);
		return product;
	}
	
	public void deleteByName(String productName) throws Exception
	{
		Product product = productRepository.findByName(productName);
		if(product == null)
		{
			throw new Exception("Bad Request: no product exists with this name");
		}
		productRepository.delete(product);
	}
}
