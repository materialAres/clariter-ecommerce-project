package com.shine.ecommerce.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @EmbeddedId
    private OrderProductKey id;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    private Integer qty;
    
    public OrderProduct() {
		// TODO Auto-generated constructor stub
	}

	public OrderProduct(OrderProductKey id, Product product, Order order, Integer qty) {
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

	public OrderProductKey getId() {
		return id;
	}

	public void setId(OrderProductKey id) {
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
