package com.capgemini.go.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.go.entity.Customer;
import com.capgemini.go.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api("Customer Service")
@RequestMapping("/customerApi")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addCustomer")
	@ApiOperation(value = "Add a Customer")
	public ResponseEntity<String> addCustomer(@ApiParam(value="Object of Customer type") @Valid @RequestBody Customer customer){
		System.out.println(customerService.addCustomer(customer));
		return new ResponseEntity<>(customerService.addCustomer(customer),HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomers")
	@ApiOperation(value = "Get List of All Customers")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
	}
	
	@PutMapping("/updateCustomer")
	@ApiOperation(value = "Update Customer Details")
	public ResponseEntity<String> updateCustomer(@ApiParam(value="Object of Customer type") @Valid  @RequestBody Customer customer){
		return new ResponseEntity<>(customerService.updateCustomer(customer),HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomerUserIds")
	@ApiOperation(value = "Get userIds of All Customers")
	public ResponseEntity<List<String>> getAllCustomerUserIds(){
		return new ResponseEntity<>(customerService.getAllCustomerUserIds(),HttpStatus.OK);
	}
	
	@GetMapping("/getCustomerByUserId/{userId}")
	@ApiOperation(value = "Get a Customer by userId")
	public ResponseEntity<Optional<Customer>> getCustomerByUserId(@ApiParam(value="userId as String") @PathVariable String userId){
		return new ResponseEntity<>(customerService.getCustomerByUserId(userId),HttpStatus.OK);
	}

}
