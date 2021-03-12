package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.IUsersDAO;
import com.capg.ftb.exception.UserNotFoundException;
import com.capg.ftb.model.Users;


@Service
public class UsersServiceImpl implements IUsersService{


	@Autowired
	private IUsersDAO usersDao;
	
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
		Optional<Users> optional=usersDao.findById(userId);
		Users user1=optional.orElseThrow(()->new UserNotFoundException("User is Not Existing with the id : "+userId));
		return user1;
	}
	

	@Override
	public List<Users> viewAllUser() {
		// TODO Auto-generated method stub
		List<Users> UserList=(List<Users>) usersDao.findAll();
		return UserList;
	}

	@Override
	public Users updateUser(int userId) {
		// TODO Auto-generated method stub
		Optional<Users> optional=usersDao.findById(userId);
		Users user4=optional.orElseThrow(()->new UserNotFoundException("User Not Existed with the id : "+userId));
		return user4;
	}

	@Override
	public Users deleteUser(int userId) {
		// TODO Auto-generated method stub
		Optional<Users> optional=usersDao.findById(userId);
		Users user1=optional.orElseThrow(()->new UserNotFoundException("User Not exisited with ID: " +userId));
		usersDao.deleteById(userId);
		return user1;
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
