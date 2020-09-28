package com.capgemini.go.service;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.go.entity.Customer;
import com.capgemini.go.exception.CustomerException;
import com.capgemini.go.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	Logger logger = LoggerFactory.getLogger(this.getClass());
			
	@Override
	public String addCustomer(Customer user){
		String userId;
		int tempUserId=customerRepository.getAllCustomerUserIds().size();
		if(tempUserId==0)
		{
			userId="U10001";
		}
		else
		{
			userId=customerRepository.getAllCustomerUserIds().get(tempUserId-1);
			tempUserId=Integer.parseInt(userId.substring(1))+1;
			userId="U"+tempUserId;
		}
		user.setUserId(userId);
		
		try
		{
			customerRepository.save(user);
		}
		catch(Exception e)
		{
			if(e.getMessage().substring(62).startsWith("mobile"))
			{
				logger.info("Exception Caught : Duplicate Mobile Number");
				throw new CustomerException("Mobile Number Already associated with another user");
			}
			if(e.getMessage().substring(62).startsWith("email"))
			{
				logger.info("Exception Caught : Duplicate Email Id");
				throw new CustomerException("Email Already accociated with another user");
			}
		}
		return "User Added successfully [ User Id : "+userId+" ]";
	}

	@Override
	public List<Customer> getAllCustomers(){
		List<Customer> users = (List<Customer>) customerRepository.findAll();
		if(users.isEmpty())
		{
			logger.info("Exception Thrown : NO USER COULD BE FOUND");
			throw new CustomerException("NO USERS FOUND");
		}
		return users;
	}

	@Override
	public Optional<Customer> getCustomerByUserId(String userId){
		Optional<Customer> user = customerRepository.findById(userId);
		if(!(user.isPresent()))
		{
			logger.info("Exception Thrown : NO USER COULD BE FOUND");
			throw new CustomerException("NO USER FOUND WITH GIVEN ID");
		}
		return user;
	}

	@Override
	public String updateCustomer(Customer user){
		try
		{
			customerRepository.save(user);
		}
		catch(Exception e)
		{
			if(e.getMessage().substring(62).startsWith("mobile"))
			{
				logger.info("Exception Caught : Duplicate Mobile Number");
				throw new CustomerException("Mobile Number Already associated with another user");
			}
			if(e.getMessage().substring(62).startsWith("email"))
			{
				logger.info("Exception Caught : Duplicate Email Id");
				throw new CustomerException("Email Already accociated with another user");
			}
		}
		return "User Details Updated Successfully";
	}

	@Override
	public List<String> getAllCustomerUserIds(){
		List<String> userIds = customerRepository.getAllCustomerUserIds();
		if(userIds.isEmpty())
		{
			logger.info("Exception Thrown : NO USER COULD BE FOUND");
			throw new CustomerException("NO USERS FOUND");
		}
		return userIds;
	}
	
	
}
