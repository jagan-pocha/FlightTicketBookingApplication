package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.IUsersDAO;
import com.capg.ftb.exception.RecordAlreadyPresentException;
import com.capg.ftb.exception.UserNotFoundException;
import com.capg.ftb.model.Users;


@Service
public class UsersServiceImpl implements IUsersService{


	@Autowired
	private IUsersDAO usersDao;
	
	@Override
	public Users addUser(Users user) {
        
		if(validateAttributes(user))
		{
		Users user1=getByName(user.getUserName());
		if(user1!=null)
		{
			throw new RecordAlreadyPresentException("User name already exited");
		}
		else
		{
			Users user2=usersDao.save(user);
			return user2;
		}	}
		else
		{
			return null;
		}
	}
	
	

	@Override
	public Users viewUser(BigInteger userId) {
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
	public Users updateUser(Users newUser,BigInteger userId) {
		// TODO Auto-generated method stub
		Optional<Users> optional=usersDao.findById(userId);
		Users user4=optional.orElseThrow(()->new UserNotFoundException("User Not Existed with the id : "+userId));
		
		// validating User type 
		if(!user4.getUserType().equals(newUser.getUserType()))
		{
			throw new UserNotFoundException("User type can not be mpdified");
		}
		if(validateAttributes(newUser))
		{
		usersDao.deleteById(userId);
		return newUser;
		}
		else
		{
			return null;
		}
	}

	@Override
	public Users deleteUser(BigInteger userId) {
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
	
	
	
	//validate User attributes
	
	public boolean validateAttributes(Users user)
	{
		Pattern number=Pattern.compile("(0/91)?[7-9][0-9]{9}");
		
		Pattern mail=Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
		
		if(!(user.getUserType().toLowerCase().equals("admin") || user.getUserType().toLowerCase().equals("customer")))
		{
			throw new UserNotFoundException("User type must be admin/customer");
		}
		else if(user.getPassword().length()<8)
		{
			throw new UserNotFoundException("Password mubt be atleast 8 characters");
		}
		else if(user.getUserName().length()<3)
		{
			throw new UserNotFoundException("User Name must be minimum 3 characters");
		}
		else if(!number.matcher(user.getMobileNumber()).find())
		{
			throw new UserNotFoundException("Number format 0/91 9999999999 . Either 10/11/12 digits");
		}
		else if(!mail.matcher(user.getEmail()).find())
		{
			throw new UserNotFoundException("should be a valid mail address EX: lahari876@gmail.com");
		}
		else
		{
		return true;
		}
	}

}
