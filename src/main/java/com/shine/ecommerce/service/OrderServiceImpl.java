package com.shine.ecommerce.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shine.ecommerce.dto.OrderDto;
import com.shine.ecommerce.entity.Order;
import com.shine.ecommerce.exceptions.EmptyOrderListException;
import com.shine.ecommerce.exceptions.InvalidIdException;
import com.shine.ecommerce.exceptions.OrderAlreadyExistsException;
import com.shine.ecommerce.exceptions.OrderNotFoundException;
import com.shine.ecommerce.repository.OrderRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private Validator validator;

	@Override
	public Order addOrder(OrderDto orderDto) throws Exception {
		Set<ConstraintViolation<OrderDto>> violations = validator.validate(orderDto);
		
	    if (!violations.isEmpty()) {
	        throw new ConstraintViolationException(violations);
	    }
	    
		Order order = Order.prepareOrderToSave(orderDto);
		
		order.setAmount(order.calculateAmount());
		order.setCreatedAt(LocalDate.now());

		return orderRepository.save(order);
	}

	@Override
	public Collection<OrderDto> getOrders() throws EmptyOrderListException {
		if (ObjectUtils.isEmpty(orderRepository.findAll())) {
			throw new EmptyOrderListException();
		}
		
		Collection<OrderDto> orders = new ArrayList<>();
		
		for (Order order : orderRepository.findAll()) {
			orders.add(new OrderDto((Order) order, false));
		}
			
		return orders;
	}
	
	@Override
	public Collection<OrderDto> getOrdersByClientId(Integer id) throws InvalidIdException, EmptyOrderListException {
		if (ObjectUtils.isEmpty(orderRepository.findAll())) {
			throw new EmptyOrderListException();
		}
		
		if (ObjectUtils.isEmpty(id)) {
			throw new InvalidIdException();
		}
		
		Collection<OrderDto> clientOrders = new ArrayList<>();
		
		for (Order order : orderRepository.findAll()) {
			if (order.getCustomer().getId() == id) {
				clientOrders.add(new OrderDto(order, false));
			}
		}
		
		return clientOrders;
	}

	@Override
	public void deleteOrder(Order order) throws EmptyOrderListException, OrderNotFoundException {
		if (ObjectUtils.isEmpty(order)) {
			throw new OrderNotFoundException();
		}
		
		if (ObjectUtils.isEmpty(orderRepository.findAll())) {
			throw new EmptyOrderListException();
		}
		
		orderRepository.delete(order);
	}

	@Override
	public void deleteOrderById(Integer id) throws EmptyOrderListException, InvalidIdException {
		if (ObjectUtils.isEmpty(id)) {
			throw new InvalidIdException();
		}
		if (ObjectUtils.isEmpty(orderRepository.findAll())) {
			throw new EmptyOrderListException();
		}
		
		orderRepository.deleteById(id);
	}

}
