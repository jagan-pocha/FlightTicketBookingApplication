package com.capg.ftb.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ftb.dao.IUsersDAO;
import com.capg.ftb.exception.UserNotFoundException;
import com.capg.ftb.model.Users;

import io.swagger.annotations.Api;

@RestController
@Api
@RequestMapping(value="/ftb")
@CrossOrigin
public class AuthenticationController {
	
	private static final Logger log =LogManager.getLogger(AirportController.class);
	
	@Autowired
	private IUsersDAO userDao;
	
	@GetMapping(value="/login/{userName}/{password}")
	public ResponseEntity<Users> login(@PathVariable String userName,@PathVariable String password)
	{
		Users user=userDao.findByUserName(userName);

		String str="";
		if(user!=null)
		{
		
		if(user.getPassword().equals(password))
		{
			str=user.getUserType().toLowerCase()+" "+userName;
		}
		else
		{
			throw new UserNotFoundException("Invalid Credentials");
		}
		}
		else
		{
			throw new UserNotFoundException("User Not Existed with given name");
		}
		log.info("User "+userName+" logged in ");
		return new ResponseEntity<Users>(user,HttpStatus.OK);
	}
	
	

}
