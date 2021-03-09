package com.capg.ftb.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public class MyAirportGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		//string to form the userId
		String str="1234678905abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRTUVWXYZ";
		
		Random r=new Random();
		Airport airport=(Airport)object;  //Airport object
		String code=new String();  // local variable 
		for(int i=0;i<5;i++)
		{
		int index=r.nextInt(str.length());    // Get a random character index from the String str
		code=code+str.charAt(index);
		}
		return code;
	}

}

