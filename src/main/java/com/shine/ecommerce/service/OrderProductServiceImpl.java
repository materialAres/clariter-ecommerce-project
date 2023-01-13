package com.shine.ecommerce.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shine.ecommerce.entity.OrderProduct;
import com.shine.ecommerce.repository.OrderProductRepository;

@Service
public class OrderProductServiceImpl {
	
	@Autowired
	OrderProductRepository orderProductRepository;
	
	public Collection<OrderProduct> getOrderProducts() throws Exception {
		if (ObjectUtils.isEmpty(orderProductRepository.findAll())) {
			throw new Exception("The list is empty.");
		}
		
		return orderProductRepository.findAll();
	}

}
