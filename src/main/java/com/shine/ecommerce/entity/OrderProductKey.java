package com.shine.ecommerce.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable		// la chiave deve essere embeddata nella entity OrderProduct
public class OrderProductKey implements Serializable {
	@Column(name = "product_id")
    Integer productId;

    @Column(name = "order_id")
    Integer orderId;
}
