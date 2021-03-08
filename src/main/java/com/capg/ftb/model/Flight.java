package com.capg.ftb.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flight {

	@Id
	private BigInteger flightNumber;
}
