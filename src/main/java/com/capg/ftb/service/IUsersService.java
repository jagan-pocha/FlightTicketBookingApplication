package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.ftb.model.Users;

public interface IUsersService {

public Users addUser(Users user);
	
	public Users viewUser(BigInteger userId);
	
	public List<Users> viewAllUser();
	
	public Users updateUser(Users newUser,BigInteger userId);
	
	public Users deleteUser(BigInteger userId);
	
	public Users getByName(String userName);
	
	public Users validateUser(Users user);
	
}
