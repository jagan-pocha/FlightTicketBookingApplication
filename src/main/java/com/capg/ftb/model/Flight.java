/* 
 * Author:Mrudhula
 * 
 */

package com.capg.ftb.model;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="Flight")
public class Flight 
{
	
	@Id
	@Min(value=555000,message="cananot be lessthan 555000")
	@Max(555999)
	private int flightNumber;
	
	
	@Column
	@Size(min=3,max=10,message="minimum three letters")
	@NotNull(message="cannot be null")
    private String carrierName; 
	
	
	@Column
	@Size(min=3,message="minimjm three letters")
	@NotNull(message="Cannot be Null and minimum length 3")
	private String flightModel;
	
	
	@Column
	@Min(30)
	@Max(120)
	@NotNull(message="Cannot be Null and between 30 and 120")
	private int seatCapacity;
	
		
	public Flight()
	{
		super();
	}
	
	public Flight(int flightNumber,String carrierName, String flightModel, Integer seatCapacity) 
	{
		super();
		this.flightNumber = flightNumber;
		this.carrierName = carrierName;
		this.flightModel = flightModel;
		this.seatCapacity = seatCapacity;
	}
	
	public int getFlightNumber()
	{
		return flightNumber;
	}
	
	public void setFlightNumber(int flightNumber) 
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

