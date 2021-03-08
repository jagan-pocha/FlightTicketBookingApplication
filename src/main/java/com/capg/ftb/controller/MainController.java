package com.capg.ftb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ftb.model.Users;
import com.capg.ftb.service.IUsersService;

@RestController
@RequestMapping(value="/ftb")
public class MainController {

	@Autowired
	private IUsersService usersService;
	
	@PostMapping(value="/addUser")
	public ResponseEntity<String> addUser(@RequestBody Users user)
	{
		Users user1=usersService.addUser(user);
		if(user1!=null)
		{
		return new ResponseEntity<String>("Welcome "+user1.getUserName()+" ! your "+user1.getUserType() +" Acount has been Created Successfully",HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<String>(" Acount has already existed with the given username",HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/login")
	public ResponseEntity<String> validateUser(@RequestBody Users user)
	{
	 
       Users user1=usersService.validateUser(user);
		if(user1!=null)
		{
		return new ResponseEntity<String>("Welcome "+user1.getUserType()+" : "+user1.getUserName()+" Thank you for loggingIn .",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>(" Invalid Credentials ",HttpStatus.OK);		
		}
	}
	
}
