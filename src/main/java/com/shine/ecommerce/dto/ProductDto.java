package com.shine.ecommerce.dto;

import java.math.BigDecimal;

import com.shine.ecommerce.entity.Product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProductDto {
	
	private Integer id;
	
    @NotBlank(message = "Name is required")
    @Size(max = 64, message = "Name must be less than 64 characters")
	private String name;
    
    @DecimalMin(value = "0", message = "Price must be greater than or equal to 0")
	private BigDecimal price;
    
    @Min(value = 0, message = "Quantity in stock must be greater than or equal to 0")
	private Integer qty;
    
	private String imageUrl;
	
	public ProductDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDto(Product product, Integer qty) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.qty = qty;
		this.setImageUrl(product.getImageUrl());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
