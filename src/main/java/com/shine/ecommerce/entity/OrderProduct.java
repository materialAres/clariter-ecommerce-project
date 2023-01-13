package com.shine.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    Integer qty;
    
    public OrderProduct() {
		// TODO Auto-generated constructor stub
	}

	public OrderProduct(Integer id, Product product, Order order, Integer qty) {
		super();
		this.id = id;
		this.product = product;
		this.order = order;
		this.qty = qty;
	}
	
	public OrderProduct(Product product, Order order, Integer qty) {
		super();
		this.product = product;
		this.order = order;
		this.qty = qty;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
    
}
