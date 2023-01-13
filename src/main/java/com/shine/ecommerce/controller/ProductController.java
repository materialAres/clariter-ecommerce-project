package com.shine.ecommerce.controller;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shine.ecommerce.dto.ProductDto;
import com.shine.ecommerce.entity.Product;
import com.shine.ecommerce.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping(path = "/product")
public class ProductController {
	
	@Autowired		
	ProductService productService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody ProductDto product) {
		try {
			return new ResponseEntity<ProductDto>(productService.addProduct(product), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Collection<ProductDto>> getProducts() {
		try {
			return new ResponseEntity<Collection<ProductDto>>(productService.getProducts(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{productName}")
	public ResponseEntity<ProductDto> getProductByName(@PathVariable String productName) {
		try {
			return new ResponseEntity<ProductDto>(
					productService.getProductByName(productName), 
					HttpStatus.OK
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<HttpStatus> deleteProduct(@RequestBody Product product) {
		try {
			productService.deleteProduct(product);
			
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
