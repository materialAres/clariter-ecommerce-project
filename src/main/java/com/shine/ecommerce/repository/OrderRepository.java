package com.shine.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.ecommerce.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
