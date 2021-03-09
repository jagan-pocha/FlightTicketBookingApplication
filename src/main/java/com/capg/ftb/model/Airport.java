/*
Author-S.N.V.Manasvi
*/
package com.capg.ftb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Airport")
public class Airport {

	@Column(name = "AirportName")
	private String airportName;

	@Column(name = "airport Location")
	private String airportLocation;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String airportCode;

	public Airport(String airportName, String airportLocation, String airportCode) {
		this.airportName = airportName;
		this.airportLocation = airportLocation;
		this.airportCode = airportCode;
	}

	// Parameterized Constructor

	public Airport() {

	}

	// Unparameterized constructor

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportLocation() {
		return airportLocation;
	}

	public void setAirportLocation(String airportLocation) {
		this.airportLocation = airportLocation;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

}
