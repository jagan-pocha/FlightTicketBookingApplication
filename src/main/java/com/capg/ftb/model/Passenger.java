/*
 * Mary Jennifer
 */
package com.capg.ftb.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

import java.math.BigInteger;

@Entity
@Table(name = "PASSENGERS")
@ApiModel
public class Passenger {

	@Id
	@GeneratedValue
	@Column(name = "pnrNumber")
	private int pnrNumber;

	@Column(name = "passengerName")
	@Size(min=3,message="Name must be atleast 3 letters")
	@NotNull(message="code cannot be bull")
	private String passengerName;

	@Column(name = "passengerAge")
	@NotNull(message="Age cannot be bull")
	private int passengerAge;

	@Column(name = "passengerUIN")
	@Size(min=12)
	private String passengerUIN;

	@Column(name = "Luggage")
	private Double Luggage;

	public int getpnrNumber() {
		return pnrNumber;
	}

	public void setpnrNumber(int pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public int getPassengerAge() {
		return passengerAge;
	}

	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}

	public @Size(min = 12, max = 12) String getPassengerUIN() {
		return passengerUIN;
	}

	public void setPassengerUIN(@Size(min = 12, max = 12) String passengerUIN) {
		this.passengerUIN = passengerUIN;
	}

	public Double getLuggage() {
		return Luggage;
	}

	public void setLuggage(Double Luggage) {
		this.Luggage = Luggage;
	}
	
	public Passenger() {
		super();
	}
	
	public Passenger(int pnrNumber, String passengerName,int passengerAge, @Size(min = 12, max = 12) String passengerUIN, Double Luggage) {
		super();
		this.pnrNumber = pnrNumber;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerUIN = passengerUIN;
		this.Luggage = Luggage;
	}
}
