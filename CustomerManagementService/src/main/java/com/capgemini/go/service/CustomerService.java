package com.capgemini.go.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.go.entity.Customer;

public interface CustomerService {
	
	List<Customer> getAllCustomers();
	Optional<Customer> getCustomerByUserId(String userId);
	String addCustomer(Customer user);
	String updateCustomer(Customer user);
	List<String> getAllCustomerUserIds();

}
