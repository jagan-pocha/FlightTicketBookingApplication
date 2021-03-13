package com.capg.ftb.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.capg.ftb.dao.IUsersDAO;
import com.capg.ftb.model.Users;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {


	@Autowired
	private IUsersService userService;

	@MockBean
	private IUsersDAO usersdao;

	@Test
	public void testAddUser() {
		BigInteger bI=new BigInteger("101");
		Users user = new Users(bI, "Customer", "Kavya", "kavya@141", "9341240438", "kavyya123@gmail.com");
		when(usersdao.save(user)).thenReturn(user);
		Users user1 = usersdao.save(user);
		assertEquals(user1, userService.addUser(user));

	}
	
	@Test
	public void testUpdateUser()
	{
		BigInteger bI=new BigInteger("101");
		Users users = new Users(bI, "Customer", "Kavya", "kavya@141", "9341240438", "kavyya123@gmail.com");
		when(usersdao.save(users)).thenReturn(users);
	    
	}

	@Test
	public void testDeleteUser()
	{
		BigInteger bI=new BigInteger("101");
		Users users=new Users(bI, "Customer", "Kavya", "kavya@141", "9341240438", "kavyya123@gmail.com");
		when(usersdao.save(users)).thenReturn(users);
		usersdao.save(users);
		usersdao.deleteById(users.getUserId());
		assertEquals(Optional.empty(),usersdao.findById(bI));
	}
	

}
