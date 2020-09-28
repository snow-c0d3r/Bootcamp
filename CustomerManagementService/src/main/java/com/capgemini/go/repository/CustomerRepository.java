package com.capgemini.go.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.go.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String>{
	
	@Query(value="SELECT u.userId FROM User u")
	public List<String> getAllCustomerUserIds();

}
