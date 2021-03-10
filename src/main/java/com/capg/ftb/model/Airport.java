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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Airport")
public class Airport {
	
	@Id
	private String airportCode;
	
	@Column
	@NotNull
	private String airportName;

	@Column
	@NotNull
	private String airportLocation;

	public Airport() {

	}

	// Unparameterized constructor

	public Airport(String airportCode,String airportName, String airportLocation) {
		this.airportName = airportName;
		this.airportLocation = airportLocation;
		this.airportCode = airportCode;
	}

	// Parameterized Constructor

	

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
