package com.shine.ecommerce.service;

import java.util.Set;

import com.shine.ecommerce.dto.OrderDto;
import com.shine.ecommerce.entity.Order;
import com.shine.ecommerce.exceptions.EmptyOrderListException;
import com.shine.ecommerce.exceptions.InvalidIdException;
import com.shine.ecommerce.exceptions.OrderNotFoundException;

public interface OrderService {

	Order addOrder(OrderDto Order) throws Exception;
	
	Set<OrderDto> getOrders() throws EmptyOrderListException;
	
	Set<OrderDto> getOrdersByClientId(Integer id) throws InvalidIdException, EmptyOrderListException;

	void deleteOrder(Order Order) throws EmptyOrderListException, OrderNotFoundException;
	
	void deleteOrderById(Integer id) throws EmptyOrderListException, InvalidIdException;

}
