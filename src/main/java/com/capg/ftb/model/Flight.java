package com.capg.ftb.model;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Flight")
public class Flight 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private BigInteger flightNumber;
	
	@Column
    private String carrierName; 
	
	@Column
	private String flightModel;
	
	@Column
	private int seatCapacity;
	
		
	public Flight()
	{
		super();
	}
	
	public Flight(BigInteger flightNumber, String carrierName, String flightModel, Integer seatCapacity) 
	{
		super();
		this.flightNumber = flightNumber;
		this.carrierName = carrierName;
		this.flightModel = flightModel;
		this.seatCapacity = seatCapacity;
	}
	
	public BigInteger getFlightNumber()
	{
		return flightNumber;
	}
	
	public void setFlightNumber(BigInteger flightNumber) 
	{
		this.flightNumber = flightNumber;
	}
	
	public String getCarrierName()
	{
		return carrierName;
	}
	
	public void setCarrierName(String carrierName)
	{
		this.carrierName = carrierName;
	}
	
	public String getFlightModel()
	{
		return flightModel;
	}
	
	public void setFlightModel(String flightModel) 
	{
		this.flightModel = flightModel;
	}
	
	public Integer getSeatCapacity() 
	{
		return seatCapacity;
	}
	
	public void setSeatCapacity(Integer seatCapacity) 
	{
		this.seatCapacity = seatCapacity;
	}
	

}

