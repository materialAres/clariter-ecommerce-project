package com.shine.ecommerce.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shine.ecommerce.dto.ProductDto;
import com.shine.ecommerce.entity.Product;
import com.shine.ecommerce.exceptions.EmptyProductListException;
import com.shine.ecommerce.exceptions.InvalidIdException;
import com.shine.ecommerce.exceptions.ProductByNameNotFoundException;
import com.shine.ecommerce.exceptions.ProductNameNotFoundException;
import com.shine.ecommerce.exceptions.ProductNotFoundException;
import com.shine.ecommerce.repository.ProductRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private Validator validator;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Set<ConstraintViolation<ProductDto>> violations = validator.validate(productDto);
		
	    if (!violations.isEmpty()) {
	        throw new ConstraintViolationException(violations);
	    }

	    productRepository.save(modelMapper.map(productDto, Product.class));

		return productDto;
	}

	@Override
	public Collection<ProductDto> getProducts() {
		return productRepository
				.findAll()
				.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public ProductDto getProductByName(String productName) throws Exception {
		if (ObjectUtils.isEmpty(productName)) {
			throw new ProductNameNotFoundException();
		}
		
		if (ObjectUtils.isEmpty(productRepository.findAll())) {
			throw new EmptyProductListException();
		}
		
		for (Product product : productRepository.findAll()) {
			if (productName.equals(((Product) product).getName())) {
				return modelMapper.map(product, ProductDto.class);
			}
		}
		
		throw new ProductByNameNotFoundException();
	}

	@Override
	public void deleteProduct(Product product) throws ProductNotFoundException, EmptyProductListException {
		if (ObjectUtils.isEmpty(productRepository.findAll())) {
			throw new EmptyProductListException();
		}
		
		if (ObjectUtils.isEmpty(product)) {
			throw new ProductNotFoundException();
		}
		
		productRepository.delete(product);
	}
	
	@Override
	public void deleteProductById(Integer id) throws EmptyProductListException, InvalidIdException {
		if (ObjectUtils.isEmpty(productRepository.findAll())) {
			throw new EmptyProductListException();
		}
		
		if (ObjectUtils.isEmpty(id)) {
			throw new InvalidIdException();
		}
		
		productRepository.deleteById(id);
	}

}
