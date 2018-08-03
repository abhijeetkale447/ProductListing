package com.rakuten.productListings.ProductListings.service;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.productListings.ProductListings.model.Category;
import com.rakuten.productListings.ProductListings.model.request.CategoryRequest;
import com.rakuten.productListings.ProductListings.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public Category getCategoryByName(String name)
	{
		Category category = categoryRepository.findByName(name);
		return category;
	}
	
	public List<Category> getAllChildCategory(String name) throws Exception{
		Set<Category> childSet = new LinkedHashSet<Category>();
		List<Category> childList = new LinkedList<Category>();
		Category category = getCategoryByName(name);
		populateChildCategory(childSet,category).stream().forEach(e -> childList.add(e));
		return childList;
	}
	
	public Category updateCategory(CategoryRequest categoryRequest) throws Exception {
		
		Category category;
		category = getCategoryByName(categoryRequest.getName());
		if(category == null) 
		{
			throw new Exception("Bad Request: category doesn't exists.");
		}
		category = populateAndValidateCategory(category, categoryRequest);
		categoryRepository.save(category);
		return category;
	}
	
	public Category saveCategory(CategoryRequest categoryRequest) throws Exception {
		Category category;
		category = getCategoryByName(categoryRequest.getName());
		if(category != null) 
		{
			throw new Exception("Bad Request: category with same name already exists.");
		}
		category = new Category();
		category = populateAndValidateCategory(category, categoryRequest);
		categoryRepository.save(category);
		return category;
	}
	
	public void deleteByName(String categoryName) throws Exception
	{
		Category category = categoryRepository.findByName(categoryName);
		if(category == null)
		{
			throw new Exception("Bad Request: no product exists with this name");
		}
		categoryRepository.delete(category);
	}
	
	private Category populateAndValidateCategory(Category category, CategoryRequest categoryRequest) throws Exception
	{
		category.setName(categoryRequest.getName());
		category.setDescription(categoryRequest.getDescription());
		Category parentCatgory = getCategoryByName(categoryRequest.getParentCategory());
		if(parentCatgory == null) {
			throw new Exception("Bad Request: Parent Category doesn't exist.");
		}
		category.setParentCategory(parentCatgory);
		return category;
	}
	
	private Set<Category> populateChildCategory(Set<Category> childSet, Category category)
	{
		childSet.add(category);
		
		for(Category child: categoryRepository.getChildCategory(category.getCategoryId()))
		{			
			populateChildCategory(childSet,child);
		}
		return childSet;
	}
}
