package com.shine.ecommerce.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shine.ecommerce.dto.CustomerDto;
import com.shine.ecommerce.dto.OrderDto;
import com.shine.ecommerce.entity.Customer;
import com.shine.ecommerce.entity.Order;
import com.shine.ecommerce.entity.OrderProduct;
import com.shine.ecommerce.entity.OrderProductKey;
import com.shine.ecommerce.exceptions.EmptyOrderListException;
import com.shine.ecommerce.exceptions.InvalidIdException;
import com.shine.ecommerce.exceptions.OrderAlreadyExistsException;
import com.shine.ecommerce.exceptions.OrderNotFoundException;
import com.shine.ecommerce.repository.OrderProductRepository;
import com.shine.ecommerce.repository.OrderRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	private final OrderProductRepository orderProductRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private Validator validator;
	
	public OrderServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

	@Override
	public OrderDto addOrder(Customer customer, Set<OrderProduct> products) throws Exception {
		for (OrderProduct orderProduct : products) {
			System.out.println(">> " + orderProduct.getProduct());
		}
		
        Order order = new Order();
        
        order.setCustomer(customer);
        order.setProducts(products);
        
        System.out.println(order.getId());
        
        order = orderRepository.save(modelMapper.map(order, Order.class));

        for (OrderProduct product : products) {
        	System.out.println(product.getProduct().getId());
        	
            product.setId(new OrderProductKey(order.getId(), product.getProduct().getId()));
            orderProductRepository.save(product);
        }
        
        return modelMapper.map(order, OrderDto.class);
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
