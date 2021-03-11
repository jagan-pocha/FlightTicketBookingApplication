/* 
 * Author- S.N.V.Manasvi
 */
package com.capg.ftb.model;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table
public class Schedule {
	
	@Id
	@Column(name = "schedule_Id")
	@Min(value=777000,message="shouldbe between 777000 and 777999")
	@Max(value=777999,message="shouldbe between 777000 and 777999")
	private int scheduleId;

	@Column
	@Size(min=3,max=25,message="Airport code not be null and minimum 3 letters")
	private String srcAirport;

	@Column
	@Size(min=3,max=25,message="Airport code not be null and minimum 3 letters")
	private String dstnAirport;

	@Column(name = "departure_date")
	@NotNull(message="Required Filed")
	@Pattern(regexp="[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]",message="Format mm-dd-yyyy")
	private String deptDate;

	@Column(name = "arrival_date")
	@NotNull(message="Required Filed")
	@Pattern(regexp="[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]",message="Format mm-dd-yyyy")
	private String arrDate;
	
	@Column(name = "arrival_time")
	@NotNull(message="Required Filed")
	@Pattern(regexp="[0-9][0-9]:[0-9][0-9]",message="Format 00:00")
	private String arrTime;
	
	@Column(name = "departure_time")
	@NotNull(message="Required Filed")
	@Pattern(regexp="[0-9][0-9]:[0-9][0-9]",message="Format 00:00")
	private String deptTime;

	/*
	 * Default constructor
	 */
	public Schedule() {

	}

	/*
	 * Parameterized constructor
	 */
	
	public Schedule(@Min(value = 777000, message = "shouldbe between 777000 and 777999") @Max(value = 777999, message = "shouldbe between 777000 and 777999") int scheduleId, String srcAirport, String dstnAirport,
			String deptDate, String arrDate,String arrTime,String deptTime) {
		super();
		this.scheduleId = scheduleId;
		this.srcAirport = srcAirport;
		this.dstnAirport = dstnAirport;
		this.deptDate = deptDate;
		this.arrDate= arrDate;
		this.arrTime=arrTime;
		this.deptTime=deptTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getDeptTime() {
		return deptTime;
	}

	public void setDeptTime(String deptTime) {
		this.deptTime = deptTime;
	}

	/*
	 * Getters and setters
	 */
	public @Min(value = 777000, message = "shouldbe between 777000 and 777999") @Max(value = 777999, message = "shouldbe between 777000 and 777999") int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(@Min(value = 777000, message = "shouldbe between 777000 and 777999") @Max(value = 777999, message = "shouldbe between 777000 and 777999") int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getSrcAirport() {
		return srcAirport;
	}

	public void setSrcAirport(String srcAirport) {
		this.srcAirport = srcAirport;
	}

	public String getDstnAirport() {
		return dstnAirport;
	}

	public void setDstnAirport(String dstnAirport) {
		this.dstnAirport = dstnAirport;
	}

	public String getDeptDate() {
		return deptDate;
	}

	public void setDeptDate(String deptDate) {
		this.deptDate = deptDate;
	}

	public String getArrDate() {
		return arrDate;
	}

	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}
}