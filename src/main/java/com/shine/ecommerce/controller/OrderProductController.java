package com.shine.ecommerce.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shine.ecommerce.entity.OrderProduct;
import com.shine.ecommerce.service.OrderProductServiceImpl;

@RestController
@CrossOrigin
@RequestMapping(path = "/order-product")
public class OrderProductController {
	
	@Autowired
	OrderProductServiceImpl orderProductService;
	
	@GetMapping("/all")
	public ResponseEntity<Collection<OrderProduct>> getOrderProducts() {
		try {
			return new ResponseEntity<Collection<OrderProduct>>(
					orderProductService.getOrderProducts(), 
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
