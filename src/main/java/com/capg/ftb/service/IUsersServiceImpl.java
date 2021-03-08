package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.UsersDAO;
import com.capg.ftb.model.Users;


@Service
public class IUsersServiceImpl implements IUsersService{

	@Autowired
	private UsersDAO usersDao;
	
	@Override
	public Users addUser(Users user) {
        
		Users user1=getByName(user.getUserName());
		if(user1!=null)
		{
			return null;
		}
		else
		{
			Users user2=usersDao.save(user);
			return user2;
		}
		
	}
	
	

	@Override
	public Users viewUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> viewUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users updateUser(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users deleteUser(Users user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users validateUser(Users user) {
		// TODO Auto-generated method stub
		Users user1=getByName(user.getUserName());
		if(user1!=null && user1.getPassword().equals(user.getPassword()))
		{
			
			return user1;
			
		}
		else
		{
			return null;
		}
	}

	@Override
	public Users getByName(String userName) {

		
		Users user1=usersDao.findByUserName(userName);
		return user1;
	}

}
