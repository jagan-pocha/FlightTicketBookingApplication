package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.ftb.model.Users;

public interface IUsersService {

public Users addUser(Users user);
	
	public Users viewUser(int userId);
	
	public List<Users> viewAllUser();
	
	public Users updateUser(int userId);
	
	public Users deleteUser(int userId);
	
	public Users getByName(String userName);
	
	public Users validateUser(Users user);
	
}
