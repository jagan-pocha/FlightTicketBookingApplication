package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.ftb.model.Users;

public interface IUsersService {

	public Users addUser(Users user);
	
	public Users viewUser(int userId);
	
	public List<Users> viewUser();
	
	public Users updateUser(Users user);
	
	public Users deleteUser(Users user);
	
	public Users getByName(String userName);
	
	public Users validateUser(Users user);
	
	
}
