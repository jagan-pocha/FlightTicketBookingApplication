package com.capg.ftb.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Booking")
public class Booking {
	
	@Id
	private int bookingId;
	@OneToOne
	private Users userId;
	@Column
	private LocalDate bookingDate;
	@OneToMany
	private List <Passenger> passengerList;
	@Column
	private double ticketCost;
	@OneToOne
	private Flight flight;
	@Column
	private int noOfPassangers;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	public Users getUserId() {
		return userId;
	}
	public void setUserId(Users userId) {
		this.userId = userId;
	}
	
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	
	public double getTicketCost() {
		return ticketCost;
	}
	public void setTicketCost(double ticketCost) {
		this.ticketCost = ticketCost;
	}
	
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public int getNoOfPassangers() {
		return noOfPassangers;
	}
	public void setNoOfPassangers(int noOfPassangers) {
		this.noOfPassangers = noOfPassangers;
	}
	
	public Booking(int bookingId,Users userId,LocalDate bookingDate,List <Passenger> passengerList,double ticketCost,Flight flight) {
		super();
		this.bookingId=bookingId;
		this.userId=userId;
		this.bookingDate=bookingDate;
		this.passengerList=passengerList;
		this.ticketCost=ticketCost;
		this.flight=flight;
	}
		
}

