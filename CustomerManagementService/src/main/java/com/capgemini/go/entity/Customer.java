package com.capgemini.go.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="user_data")
public class Customer {
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_NAME")
	@NotNull
	@Size(min = 2,message="Username must have atleast 2 characters")
	@Pattern(regexp = "[a-zA-Z0-9]*$", message = "Username must be Alphanumeric without spaces" )
	private String userName;
	
	@Column(name="ADDRESS")
	@NotNull
	private String address;
	
	@Column(name="ZIPCODE")
	@NotNull
	@Size(min = 6, max = 6, message="ZipCode must be a 6 digit number")
	@Pattern(regexp = "[0-9]+", message = "ZipCode must be a 6 digit number")
	private String zipcode;
	
	@Column(name="EMAIL")
	@NotNull
	@Email(message = "Invalid Email Id")
	private String email;
	
	@Column(name="MOBILE")
	@NotNull
	@Size(min = 10, max = 10, message="Mobile Number must be a 10 digit Number Starting with either 7,8 or 9")
	@Pattern(regexp = "[789][0-9]+", message = "Mobile Number must be a 10 digit Number Starting with either 7,8 or 9")
	private String mobile;

	public Customer() {}
	
	public Customer(String userName, String address, String zipcode, String email, String mobile) {
		super();
		this.userName = userName;
		this.address = address;
		this.zipcode = zipcode;
		this.email = email;
		this.mobile = mobile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
}
