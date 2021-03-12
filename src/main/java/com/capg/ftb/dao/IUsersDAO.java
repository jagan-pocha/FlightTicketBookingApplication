package com.capg.ftb.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.ftb.model.Users;

@Repository
public interface IUsersDAO extends JpaRepository<Users,Integer>{

	Users findByUserName(String userName);
	
	
}
