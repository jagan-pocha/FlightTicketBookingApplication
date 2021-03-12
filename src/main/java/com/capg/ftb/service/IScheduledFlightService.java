/*
 * Jagan mohan
 */
package com.capg.ftb.service;

import java.math.BigInteger;
import java.util.List;

import com.capg.ftb.model.ScheduledFlight;

public interface IScheduledFlightService {

	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);

	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight,BigInteger scheduleFlightId);

	public void removeScheduledFlight(BigInteger scheduleFlightId);// throws RecordNotFoundException;

	public List<ScheduledFlight> viewAllScheduledFlights();

	public ScheduledFlight viewScheduledFlight(BigInteger scheduledFlightId); // throws ScheduledFlightNotFoundException;
	
	public List<ScheduledFlight> searchScheduledFlight(String srcAirport,String dstnAirport,String deptDate);
	
	public boolean validate(ScheduledFlight scheduledFlight);
}
