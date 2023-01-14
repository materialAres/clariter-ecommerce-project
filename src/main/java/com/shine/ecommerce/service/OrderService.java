package com.shine.ecommerce.service;

import java.util.Collection;
import java.util.Set;

import com.shine.ecommerce.dto.CustomerDto;
import com.shine.ecommerce.dto.OrderDto;
import com.shine.ecommerce.dto.ProductDto;
import com.shine.ecommerce.entity.Customer;
import com.shine.ecommerce.entity.Order;
import com.shine.ecommerce.entity.OrderProduct;
import com.shine.ecommerce.exceptions.EmptyOrderListException;
import com.shine.ecommerce.exceptions.InvalidIdException;
import com.shine.ecommerce.exceptions.OrderNotFoundException;

public interface OrderService {

	OrderDto addOrder(Customer customer, Set<OrderProduct> products) throws Exception;
	
	Collection<OrderDto> getOrders() throws EmptyOrderListException;
	
	Collection<OrderDto> getOrdersByClientId(Integer id) throws InvalidIdException, EmptyOrderListException;

	void deleteOrder(Order Order) throws EmptyOrderListException, OrderNotFoundException;
	
	void deleteOrderById(Integer id) throws EmptyOrderListException, InvalidIdException;

}
