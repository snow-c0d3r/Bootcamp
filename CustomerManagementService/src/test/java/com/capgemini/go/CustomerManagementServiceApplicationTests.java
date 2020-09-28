package com.capgemini.go;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import com.capgemini.go.entity.Customer;
import com.capgemini.go.exception.CustomerException;
import com.capgemini.go.service.CustomerService;

@SpringBootTest
class CustomerManagementServiceApplicationTests {

	@Autowired
	private CustomerService userService;
	
	private static Validator validator;

	   @BeforeAll
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }
	   
	   @Test
	   void mobileIsAlphabetic(){
		   Customer user = new Customer("Sahil","Punjab","140413","sahil@mail.com","986723as90");
		   Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		   assertEquals("Mobile Number must be a 10 digit Number Starting with either 7,8 or 9",constraintViolations.iterator().next().getMessage());
		   }
	   
	   @Test
	   void mobileIsBlank() {
		   Customer user = new Customer("Sahil","Punjab","140413","sahil@mail.com"," ");
		   Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		   assertEquals("Mobile Number must be a 10 digit Number Starting with either 7,8 or 9",constraintViolations.iterator().next().getMessage());
	   }
	   
	   @Test
	   void mobileFormatIsWrong() {
		   Customer user = new Customer("Sahil","Punjab","140413","sahil@mail.com","5679873423");
		   Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		   assertEquals("Mobile Number must be a 10 digit Number Starting with either 7,8 or 9",constraintViolations.iterator().next().getMessage());
	   }
	  
	   @Test
	   void mobileIsLessThanTenDigits()
	   {
		   Customer user = new Customer("Sahil","Punjab","140413","sahil@mail.com","98059058");
		   Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		   assertEquals("Mobile Number must be a 10 digit Number Starting with either 7,8 or 9",constraintViolations.iterator().next().getMessage());
	   }
	   
	   @Test
	   void mobileIsMoreThanTenDigits()
	   {
		   Customer user = new Customer("Sahil","Punjab","140413","sahil@mail.com","919805905823");
		   Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		   assertEquals("Mobile Number must be a 10 digit Number Starting with either 7,8 or 9",constraintViolations.iterator().next().getMessage());
	   }
	   
	@Test
	void testAddUserWithDuplicateMobile() throws CustomerException
	{
		Customer user = new Customer("Sahil","Punjab","140413","sahil@mail.com","8894672528");
		Assertions.assertThrows(Exception.class, () -> userService.addCustomer(user));
	}

	@Test
	void testAddUserWithDuplicateEmail() throws CustomerException
	{
		Customer user = new Customer("Sahil","Punjab","140413","pals.22oct98@gmail.com","8894672528");
		Assertions.assertThrows(Exception.class, () -> userService.addCustomer(user));
	}
	
//	@Test
//	void testAddUserWithCorrectMobile() throws UserException
//	{
//		User user = new User("Sahil","Punjab","140413","sahil@gmail.com","9805905823");
//		assertEquals("User Added successfully [ User Id : U10005 ]", userService.addUser(user));
//	}

	@Test
	void emailIsInvalid() {
		Customer user = new Customer("Rishabh","Ludhiana","141016","rishabhhhh","8454671112");
		Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		   assertEquals("Invalid Email Id",constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	void emailIsBlank()
	{
		Customer user = new Customer("Rishabh","Ludhiana","141016","  ","8454671112");
		Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		   assertEquals("Invalid Email Id",constraintViolations.iterator().next().getMessage());
	}
	
//	 @Test void testAddUserWithCorrectEmail() throws UserException { User user =
//	 new User("Rishabh","Ludhiana","141016","rishabh.ss@yahoomail.com","8454671112");
//	 assertEquals("User Added successfully [ User Id : U10006 ]",
//	 userService.addUser(user)); }
	 
	@Test
	void userNameContainsSpecialChar()
	{
		Customer user = new Customer("Adity@","Mumbai","400615","aadi123@hotmail.com","7789854898");
		Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		assertEquals("Username must be Alphanumeric without spaces",constraintViolations.iterator().next().getMessage());
	}

	@Test
	void userNameISBlank() {
		Customer user = new Customer("   ","Mumbai","400615","aadi123@hotmail.com","7789854898");
		Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		assertEquals("Username must be Alphanumeric without spaces",constraintViolations.iterator().next().getMessage());
	}
	
//	@Test
//	void testAddUserWithCorrectUserName() throws UserException { User user
//	= new User("Aditya","Mumbai","400615","aadi123@hotmail.com","7789854898");
//	assertEquals("User Added successfully [ User Id : U10007 ]",
//	userService.addUser(user)); }
	
	@Test
	void zipCodeIsAlphabetic()
	{
		Customer user = new Customer("Rajat","Himachal","176asd","raja.t98@gmail.com","9899278487");
		Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		assertEquals("ZipCode must be a 6 digit number",constraintViolations.iterator().next().getMessage());
	}
	
	@Test
	void zipCodeIsBlank()
	{
		Customer user = new Customer("Rajat","Himachal"," ","raja.t98@gmail.com","9899278487");
		Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		assertEquals("ZipCode must be a 6 digit number",constraintViolations.iterator().next().getMessage());
	}
	@Test
	void zipCodeIsLessThanSixDigits()
	{
		Customer user = new Customer("Rajat","Himachal","17605","raja.t98@gmail.com","9899278487");
		Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		assertEquals("ZipCode must be a 6 digit number",constraintViolations.iterator().next().getMessage());
	}
	@Test
	void zipCodeIsMoreThanSixDigits()
	{
		Customer user = new Customer("Rajat","Himachal","1760571","raja.t98@gmail.com","9899278487");
		Set<ConstraintViolation<Customer>> constraintViolations =validator.validate( user );
		assertEquals("ZipCode must be a 6 digit number",constraintViolations.iterator().next().getMessage());
	}

//	@Test
//	void testAddUserWithCorrectZipCode() throws UserException
//	{
//		User user = new User("Rajat","Himachal","176057","raja.t98@gmail.com","9899278487");
//		assertEquals("User Added successfully [ User Id : U10008 ]", userService.addUser(user));
//	}
	
	@Test
	void testGetUserByIdWithExistingUser() throws CustomerException
	{
		String testUserId="U10003";
		assertNotEquals(null,userService.getCustomerByUserId(testUserId));
	}
	
	@Test
	void testGetUserByIdWithNonExistingUser() throws CustomerException
	{
		String testUserId="U10008";
		Assertions.assertThrows(Exception.class, () -> userService.getCustomerByUserId(testUserId));
	}
	
	@Test
	void testGetAllUsers() throws CustomerException
	{
		List <Customer> users = userService.getAllCustomers();
		assertFalse(users.isEmpty());
	}

	
}
