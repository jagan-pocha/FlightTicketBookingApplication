package com.capg.ftb.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Users {

	
	@Id
 	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator",strategy = "com.capg.ftb.model.MyGenerator")	
	private int userId;
	
	@Column
	@Size(min=5,max=8,message="type should be admin/customer")
	private String userType;
	
	@Column
	@Size(min=3,message="Name can not be less than 3 letters")
	private String userName;
	
	@Column
	@Size(min=8,message="password should be atleast 8 letters")
	private String password;
	
	@Column
	@Min(value=10,message="Number should be 10 digits")
	@Max(value=10,message="Number should be 10 digits")
	@Pattern(regexp="[1-9][0-9]{9}",message="Number must not start with 0")
	private String mobileNumber;
	
	@Column
	private String email;
	
	
	
	public Users() {
		super();
	}

	public Users(int userId, String userType, String userName, String password, String email,
			String mobileNumber) {
		super();
		this.userId = userId;
		this.userType = userType;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
	

}
