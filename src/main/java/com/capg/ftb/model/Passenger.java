package com.capg.ftb.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "PASSENGERS")
public class Passenger {

	@Id
	@GeneratedValue
	@Column(name = "pnrNumber")
	private int pnrNumber;

	@Column(name = "passengerName")
	private String passengerName;

	@Column(name = "passengerAge")
	private int passengerAge;

	@Column(name = "passengerUIN")
	private BigInteger passengerUIN;

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

	public BigInteger getPassengerUIN() {
		return passengerUIN;
	}

	public void setPassengerUIN(BigInteger passengerUIN) {
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
	
	public Passenger(int pnrNumber, String passengerName,int passengerAge, BigInteger passengerUIN, Double Luggage) {
		super();
		this.pnrNumber = pnrNumber;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerUIN = passengerUIN;
		this.Luggage = Luggage;
	}
}
