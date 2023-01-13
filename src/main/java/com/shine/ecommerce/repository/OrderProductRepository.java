package com.shine.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.ecommerce.entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

}
