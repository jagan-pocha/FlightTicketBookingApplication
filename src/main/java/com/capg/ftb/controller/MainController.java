package com.capg.ftb.controller;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.ftb.exception.UserNotFoundException;
import com.capg.ftb.model.Flight;
import com.capg.ftb.model.Users;
import com.capg.ftb.service.IFlightService;
import com.capg.ftb.service.IUsersService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value="/ftb")
@Validated
@Api
@CrossOrigin
public class MainController {

	private static final Logger log =LogManager.getLogger(MainController.class);
	
	@Autowired
	private IUsersService usersService;
	
	//User Services
	
	@Autowired(required= true)
	private IUsersService userService;
	
	@PostMapping(value="/addUser")
	public ResponseEntity<Users> addUser(@Valid @RequestBody Users newUser) {

		
		Users user1=userService.addUser(newUser);
		log.info("Added User ");
		return new ResponseEntity<Users>(user1,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<Users> updateUser(@RequestBody Users newUser,@PathVariable BigInteger userId) {

		Users user2=userService.updateUser(newUser,userId);
		log.info("Updated User ");
		return new ResponseEntity<Users>(user2,HttpStatus.OK);
	}
	
	@GetMapping("/viewUser/{userId}")
	public ResponseEntity<Users> viewUser(@PathVariable("userId") BigInteger userId) {
		
		Users user3=userService.viewUser(userId);
		log.info("Viewed details");
		return new ResponseEntity<Users>(user3,HttpStatus.OK);
	}
	
	@GetMapping("/viewAllUser")
	public Iterable<Users> viewAllUser() {

		log.info("viewed all users");
		return userService.viewAllUser();
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<Users> deleteUser(@PathVariable("userId") BigInteger userId) {

		Users user4=userService.deleteUser(userId);
		log.info("Deleted User ");
		return new ResponseEntity<Users>(user4,HttpStatus.OK);
		
		
	}
	
	@GetMapping(value="/validateUser")
	public ResponseEntity<String> validateUser(@RequestBody Users user)
	{
	 
       Users user1=userService.validateUser(user);
       log.info("Validating  User Details");
		if(user1!=null)
		{
		return new ResponseEntity<String>("User ID: "+user.getUserId()+" with User Name: "+user.getUserName()+" is registerd.",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>(" User Not Found/Invalid Credentials ",HttpStatus.OK);		
		}
	}
	
	
	
	
	
}
