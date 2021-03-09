//package com.capg.ftb.model;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
////import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//
//@Entity
//@Table
//public class ScheduledFlight {
//	
//	
//	@Column(name="Flight Id")
//	private int flightId;
//	
//	@Column(name="Schedule Flight State")
//	private String scheduleFlightState;
////
////	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
////	@JoinColumn(name="flight_fk")
////	private Flight flight;  // instance of Flight class in ScheduledFlight class
//	
//	@Column(name="Available Seats")
//	private int availableSeats;
//	
//	
//	
//	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name="schedule_fk")
//	private Schedule schedule;
//	
//	// Default Constructor
//	public ScheduledFlight() {
//		super();
//	}
//	
//	// Parametric Constructor
//	public ScheduledFlight(int flightId, String scheduleFlightState,  int availableSeats,
//			Schedule schedule) {
//		super();
//		this.flightId = flightId;
//		this.scheduleFlightState = scheduleFlightState;
//		//this.flight = flight;
//		this.availableSeats = availableSeats;
//		this.schedule = schedule;
//	}
//	
//	
//	public int getFlightId() {
//		return flightId;
//	}
//
//
//
//	public void setFlightId(int flightId) {
//		this.flightId = flightId;
//	}
//
//
//
//	public String getScheduleFlightState() {
//		return scheduleFlightState;
//	}
//
//
//
//	public void setScheduleFlightState(String scheduleFlightState) {
//		this.scheduleFlightState = scheduleFlightState;
//	}
//
//
//
////	public Flight getFlight() {
////		return flight;
////	}
////
////
////
////	public void setFlight(Flight flight) {
////		this.flight = flight;
////	}
//
//
//
//	public int getAvailableSeats() {
//		return availableSeats;
//	}
//
//
//
//	public void setAvailableSeats(int availableSeats) {
//		this.availableSeats = availableSeats;
//	}
//
//
//
//	public Schedule getSchedule() {
//		return schedule;
//	}
//
//
//
//	public void setSchedule(Schedule schedule) {
//		this.schedule = schedule;
//	}
//
//
//	
//	
//}
