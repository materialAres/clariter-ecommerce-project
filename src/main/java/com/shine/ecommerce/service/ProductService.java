package com.shine.ecommerce.service;

import java.util.Collection;

import com.shine.ecommerce.dto.ProductDto;
import com.shine.ecommerce.entity.Product;
import com.shine.ecommerce.exceptions.EmptyProductListException;
import com.shine.ecommerce.exceptions.InvalidIdException;
import com.shine.ecommerce.exceptions.ProductNameNotFoundException;
import com.shine.ecommerce.exceptions.ProductNotFoundException;

public interface ProductService {
	
	ProductDto addProduct(ProductDto Product);
	
	Collection<ProductDto> getProducts();
	
	ProductDto getProductByName(String productName) throws ProductNameNotFoundException, EmptyProductListException, Exception;

	void deleteProduct(Product Product) throws ProductNotFoundException, EmptyProductListException;

	void deleteProductById(Integer id) throws EmptyProductListException, InvalidIdException;

}
