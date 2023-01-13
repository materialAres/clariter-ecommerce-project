package com.shine.ecommerce.dto;

import com.shine.ecommerce.entity.Order;
import com.shine.ecommerce.entity.OrderProduct;
import com.shine.ecommerce.entity.Product;

public class OrderProductDto {
	
    private Integer id;

    private Product product;

    private Order order;

    private Integer qty;
    
    public OrderProductDto() {
		// TODO Auto-generated constructor stub
	}
    
    public OrderProductDto(OrderProduct orderProduct) {
		this.id = orderProduct.getId();
		this.product = orderProduct.getProduct();
		this.order = orderProduct.getOrder();
		this.qty = orderProduct.getQty();
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

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

}
