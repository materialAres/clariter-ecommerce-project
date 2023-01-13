package com.shine.ecommerce.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shine.ecommerce.dto.CustomerDto;
import com.shine.ecommerce.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping(path = "/customer")
public class CustomerController {

	@Autowired		
	CustomerService customerService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerDto customer) {
		try {
			
			return new ResponseEntity<CustomerDto>(
					new CustomerDto(
							customerService.addCustomer(customer),
							true
							), 
					HttpStatus.CREATED
					);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<Collection<CustomerDto>> getCustomers() {
		try {
			return new ResponseEntity<Collection<CustomerDto>>(
					customerService.getCustomers(), 
					HttpStatus.OK
					);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
