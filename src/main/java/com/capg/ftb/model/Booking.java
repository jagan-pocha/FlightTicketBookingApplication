/*
 * Aithor :Thiracy Mary
 */

package com.capg.ftb.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Booking")
public class Booking {
	
	@Id
	@GeneratedValue
	private int bookingId;
	@Column
	private int userId;
	@Column
	private LocalDate bookingDate;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="bookedById") 
	private List<Passenger> passengerList;
	@Column
	private double ticketCost;
	@Column
	private int scheduledFlightId;
	@Column
	@Min(value=1,message="atleast one passenger required")
	private int noOfPassangers;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
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
	
	
	public Booking() {
		super();
	}
	public int getNoOfPassangers() {
		return noOfPassangers;
	}
	public void setNoOfPassangers(int noOfPassangers) {
		this.noOfPassangers = noOfPassangers;
	}
	
	public Booking(int bookingId,int userId,LocalDate bookingDate,List <Passenger> passengerList,double ticketCost,int scheduledFlightId) {
		super();
		this.bookingId=bookingId;
		this.userId=userId;
		this.bookingDate=bookingDate;
		this.passengerList=passengerList;
		this.ticketCost=ticketCost;
		this.scheduledFlightId=scheduledFlightId;
	}
	public int getScheduledFlightId() {
		return scheduledFlightId;
	}
	public void setScheduledFlightId(int scheduledFlightId) {
		this.scheduledFlightId = scheduledFlightId;
	}
		
}

