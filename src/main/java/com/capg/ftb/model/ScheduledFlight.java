package com.capg.ftb.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
//import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
public class ScheduledFlight {

	@Id
	private int scheduleFlightId;

	@Column
	private int flight;

	@Column(name = "availableseats")
	@NotNull
	private Integer availableSeats;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	private Schedule schedule;

	/*
	 * Default constructor
	 */
	public ScheduledFlight() {

	}

	/*
	 * Parameterized constructor
	 */
	public ScheduledFlight(int scheduleFlightId, int flight, Integer availableSeats,
			Schedule schedule) {
		super();
		this.scheduleFlightId = scheduleFlightId;
		this.flight = flight;
		this.availableSeats = availableSeats;
		this.schedule = schedule;
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
