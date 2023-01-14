package com.shine.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.ObjectUtils;

import com.shine.ecommerce.dto.OrderDto;
import com.shine.ecommerce.dto.ProductDto;
import com.shine.ecommerce.exceptions.EmptyOrderListException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<OrderProduct> products;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@DecimalMin(value = "0", message = "Amount must be greater than or equal to 0")
	private BigDecimal amount;
	
	@PastOrPresent
	private LocalDate createdAt;
	
	public Order() {}

	public Order(Integer id, Set<OrderProduct> products) throws Exception {
		super();
		this.id = id;
		this.products = products;
		this.amount = calculateAmount();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal calculateAmount() throws EmptyOrderListException {
		if (ObjectUtils.isEmpty(getProducts())) {
			throw new EmptyOrderListException();
		}
		
		amount = new BigDecimal(0.0);
		
		for (OrderProduct product : products) {
			amount = amount.add((product.getProduct().getPrice()).multiply(new BigDecimal(product.getQty())));
		}
		
		return getAmount().multiply(new BigDecimal(100.0)).divide(new BigDecimal(100.0));
	}

	public Set<OrderProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<OrderProduct> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

}