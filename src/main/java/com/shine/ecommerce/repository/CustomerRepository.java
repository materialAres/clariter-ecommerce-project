package com.shine.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shine.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
