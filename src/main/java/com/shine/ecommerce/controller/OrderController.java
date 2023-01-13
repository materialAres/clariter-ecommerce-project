package com.shine.ecommerce.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shine.ecommerce.dto.OrderDto;
import com.shine.ecommerce.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping(path = "/order")
public class OrderController {
	
	@Autowired		
	OrderService orderService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addOrder(@RequestBody OrderDto order) {
		try {
			return new ResponseEntity<OrderDto>(
					new OrderDto(
							orderService.addOrder(order),
							true
							), 
					HttpStatus.CREATED
					);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Collection<OrderDto>> getOrders() {
		try {
			return new ResponseEntity<Collection<OrderDto>>(orderService.getOrders(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/by-client-id/{id}")
	public ResponseEntity<Collection<OrderDto>> getOrderByClientId(@PathVariable Integer id) {
		try {
			return new ResponseEntity<Collection<OrderDto>>(
					orderService.getOrdersByClientId(id), 
					HttpStatus.OK
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
