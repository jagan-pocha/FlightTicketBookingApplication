package com.capg.ftb.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Users {

	
	@Id
 	@GeneratedValue(generator = "IdGenerator")
	@GenericGenerator(name = "IdGenerator",strategy = "com.capg.ftb.model.MyGenerator")	
	private int userId;
	
	@Column
	private String userType;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
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
