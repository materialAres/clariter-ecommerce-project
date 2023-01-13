package com.shine.ecommerce.service;

import java.util.Collection;

import com.shine.ecommerce.dto.CustomerDto;
import com.shine.ecommerce.entity.Customer;
import com.shine.ecommerce.exceptions.CustomerAlreadyExistsException;
import com.shine.ecommerce.exceptions.CustomerNotFoundException;
import com.shine.ecommerce.exceptions.EmptyCustomerListException;
import com.shine.ecommerce.exceptions.InvalidIdException;

public interface CustomerService {

	CustomerDto addCustomer(CustomerDto customer) throws CustomerNotFoundException, CustomerAlreadyExistsException;
	
	Collection<CustomerDto> getCustomers() throws EmptyCustomerListException;

	void deleteCustomer(Customer customer) throws EmptyCustomerListException, CustomerNotFoundException;
	
	void deleteCustomerById(Integer id) throws EmptyCustomerListException, InvalidIdException;

}
