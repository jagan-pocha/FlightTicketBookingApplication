package com.capg.ftb.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
//import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
public class ScheduledFlight {

	@Id
	@Min(value=999000,message="Id should between 999000 & 999999")
	@Max(value=999999,message="Id should between 999000 & 999999")
	private int scheduleFlightId;

	@Column
	@Min(value=555000,message="flight Id should between 555000 & 555999")
	@Max(value=555999,message="flight Id should between 555000 & 555999")
	private int flight;

	@Column(name = "availableseats")
	@Min(value=10,message="minimum seats 10 required")
	private Integer availableSeats;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Schedule schedule;
	
	@NotNull
	@Min(value=(long) 500.0,message="minimum cost 500.0")
	private double costPerHead;

	public double getCostPerHead() {
		return costPerHead;
	}

	public void setCostPerHead(double costPerHead) {
		this.costPerHead = costPerHead;
	}

	
	/*
	 * Default constructor
	 */
	public ScheduledFlight() {

	}

	/*
	 * Parameterized constructor
	 */
	public ScheduledFlight(int scheduleFlightId, int flight, Integer availableSeats,
			Schedule schedule,double costPerHead) {
		super();
		this.scheduleFlightId = scheduleFlightId;
		this.flight = flight;
		this.availableSeats = availableSeats;
		this.schedule = schedule;
		this.costPerHead=costPerHead;
	}

	/*
	 * Getter and setter for ID
	 */
	public int getScheduleFlightId() {
		return scheduleFlightId;
	}

	public void setScheduleFlightId(int scheduleFlightId) {
		this.scheduleFlightId = scheduleFlightId;
	}

	/*
	 * Getter and setter for Available seats
	 */
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	/*
	 * Getter and setter for Flight object
	 */
	public int getFlight() {
		return flight;
	}

	public void setFlight(int flight) {
		this.flight = flight;
	}

	/*
	 * Getter and setter for Schedule object
	 */
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
