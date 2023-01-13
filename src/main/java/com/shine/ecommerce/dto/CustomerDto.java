package com.shine.ecommerce.dto;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shine.ecommerce.entity.Customer;
import com.shine.ecommerce.entity.Order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerDto {
	
	private Integer id;
	
    @NotBlank(message = "Name is required")
    @Size(max = 64, message = "Name must be less than 64 characters")
	private String name;
    
    @NotBlank(message = "Surname is required")
    @Size(max = 64, message = "Surname must be less than 64 characters")
	private String surname;
    
	private Set<OrderDto> orders;
	
	public CustomerDto() {
		// TODO Auto-generated constructor stub
	}

	public CustomerDto(Customer customer, boolean lazy) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.surname = customer.getSurname();
		
		if (!lazy) {
			orders = new HashSet<>();
			
			for (Iterator<Order> iterator = customer.getOrders().iterator(); iterator.hasNext();) {
				orders.add(new OrderDto((Order) iterator.next()));
			}
		}
	}

	public CustomerDto(CustomerDto customer, boolean lazy) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.surname = customer.getSurname();
		
		if (!lazy) {
			orders = new HashSet<>();
			
			for (OrderDto order : customer.getOrders()) {
				orders.add(new OrderDto(order.getId(), order.getAmount()));
			}
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Set<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDto> orders) {
		this.orders = orders;
	}
	
}
