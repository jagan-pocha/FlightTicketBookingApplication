package com.capg.ftb.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public class MyGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		//string to form the userId
		String str="1234678905abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRTUVWXYZ";
		
		Random r=new Random();
		int pkId=00;   // local variable 
		
		Users user=(Users)object;  //User object
		for(int i=0;i<user.getUserType().length();i++)
		{
		int index=r.nextInt(str.length());    // Get a random character index from the String str
		pkId=pkId+(int)(Math.pow(10,i)+(int)str.charAt(index));
		}
		return pkId;
	}

}
