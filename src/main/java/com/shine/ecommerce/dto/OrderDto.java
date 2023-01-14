package com.shine.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.shine.ecommerce.entity.Order;
import com.shine.ecommerce.entity.OrderProduct;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class OrderDto {

	private Integer id;
	
	private Set<OrderProduct> products;
	
	@DecimalMin(value = "0", message = "Amount must be greater than or equal to 0")
	private BigDecimal amount;
	
	@NotNull
	private CustomerDto customer;
	
	@PastOrPresent
	private LocalDate createdAt;
	
	public OrderDto() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderDto(Order order) {
		super();
		this.id = order.getId();
		this.amount = order.getAmount();
	}
	
	public OrderDto(Order order, boolean lazy) {
		super();
		this.id = order.getId();
		this.amount = order.getAmount();
		this.customer = new CustomerDto(order.getCustomer(), true);
		this.createdAt = order.getCreatedAt();
		
		if (!lazy) {
			products = new HashSet<>();
			
			for (OrderProduct orderProduct : order.getProducts()) {
				products.add(orderProduct);
			}
		}
	}

	public OrderDto(Integer id, BigDecimal amount) {
		this.id = id;
		this.amount = amount;
	}

	public OrderDto(OrderDto order, boolean lazy) {
		super();
		this.id = order.getId();
		this.amount = order.getAmount();
		this.customer = new CustomerDto(order.getCustomer(), true);
		this.createdAt = order.getCreatedAt();
		
		if (!lazy) {
			products = new HashSet<>();
			
			for (OrderProduct orderProduct : order.getProducts()) {
				products.add(orderProduct);
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<OrderProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<OrderProduct> products) {
		this.products = products;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	
}
