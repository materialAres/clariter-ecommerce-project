package com.shine.ecommerce.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shine.ecommerce.dto.CustomerDto;
import com.shine.ecommerce.dto.ProductDto;
import com.shine.ecommerce.entity.Customer;
import com.shine.ecommerce.entity.Product;
import com.shine.ecommerce.exceptions.CustomerAlreadyExistsException;
import com.shine.ecommerce.exceptions.CustomerNotFoundException;
import com.shine.ecommerce.exceptions.EmptyCustomerListException;
import com.shine.ecommerce.exceptions.InvalidIdException;
import com.shine.ecommerce.repository.CustomerRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private Validator validator;

	@Override
	public CustomerDto addCustomer(CustomerDto customerDto) throws CustomerNotFoundException, CustomerAlreadyExistsException {
		Set<ConstraintViolation<CustomerDto>> violations = validator.validate(customerDto);
		
	    if (!violations.isEmpty()) {
	        throw new ConstraintViolationException(violations);
	    }

	    customerRepository.save(modelMapper.map(customerDto, Customer.class));

		return customerDto;
	}

	@Override
	public Collection<CustomerDto> getCustomers() throws EmptyCustomerListException {
        List<CustomerDto> customerDtos = new ArrayList<>();
        
        for (Customer customer : customerRepository.findAll()) {
        	customerDtos.add(new CustomerDto(customer, false));
        }
        
		return customerDtos;
	}

	@Override
	public void deleteCustomer(Customer customer) throws EmptyCustomerListException, CustomerNotFoundException {
		if (ObjectUtils.isEmpty(customer)) {
			throw new CustomerNotFoundException();
		}
		
		if (ObjectUtils.isEmpty(customerRepository.findAll())) {
			throw new EmptyCustomerListException();
		}
		
		customerRepository.delete(customer);
	}

	@Override
	public void deleteCustomerById(Integer id) throws EmptyCustomerListException, InvalidIdException {
		if (ObjectUtils.isEmpty(id)) {
			throw new InvalidIdException();
		}
		
		if (ObjectUtils.isEmpty(customerRepository.findAll())) {
			throw new EmptyCustomerListException();
		}
		
		customerRepository.deleteById(id);
		
	}

}
