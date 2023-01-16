package com.shine.ecommerce.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shine.ecommerce.dto.OrderDto;
import com.shine.ecommerce.entity.Order;
import com.shine.ecommerce.exceptions.EmptyOrderListException;
import com.shine.ecommerce.exceptions.InvalidIdException;
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
	public SortedSet<OrderDto> getOrders() throws EmptyOrderListException {
		Set<OrderDto> unsortedOrders = orderRepository
				.findAll()
				.stream()
				.map(order -> modelMapper.map(order, OrderDto.class))
				.collect(Collectors.toSet());
		
		TreeSet<OrderDto> sortedOrders = new TreeSet<>(unsortedOrders);
		
		return sortedOrders;
	}
	
	@Override
	public Set<OrderDto> getOrdersByClientId(Integer id) throws InvalidIdException, EmptyOrderListException {
		if (ObjectUtils.isEmpty(orderRepository.findAll())) {
			throw new EmptyOrderListException();
		}
		
		if (ObjectUtils.isEmpty(id)) {
			throw new InvalidIdException();
		}
		
		Set<OrderDto> clientOrders = new HashSet<>();
		
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
