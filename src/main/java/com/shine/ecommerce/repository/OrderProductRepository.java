package com.shine.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.ecommerce.entity.OrderProduct;
import com.shine.ecommerce.entity.OrderProductKey;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {

}
