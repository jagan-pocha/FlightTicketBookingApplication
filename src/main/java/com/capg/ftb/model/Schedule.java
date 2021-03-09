/* 
 * Author- S.N.V.Manasvi
 */
package com.capg.ftb.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Schedule")
public class Schedule {
	
	@OneToOne
	public Airport sourceAirport;

	@OneToOne
	public Airport destinationAirport;

	@Column(name = "ArrivalTime")
	public LocalDateTime arrivalTime;

	@Column(name = "DepartureTime")
	public LocalDateTime departureTime;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String scheduleId;

	public Schedule(Airport sourceAirport, Airport destinationAirport, LocalDateTime arrivalTime,
			LocalDateTime departureTime, String scheduleId) {
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.scheduleId = scheduleId;
	}
	// Parameterized Constructor

	public Schedule() {

	}
	// Unparameterized Constructor

public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;

	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;

	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;

	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;

	}

	public void setscheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
}