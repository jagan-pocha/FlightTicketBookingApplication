package com.capg.ftb.service;

import java.util.List;

import com.capg.ftb.model.ScheduledFlight;

public interface IScheduledFlightService {

	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight);

	public ScheduledFlight modifyScheduledFlight(int scheduleFlightId);

	public void removeScheduledFlight(int scheduleFlightId);// throws RecordNotFoundException;

	public List<ScheduledFlight> viewAllScheduledFlights();

	public ScheduledFlight viewScheduledFlight(int scheduledFlightId); // throws ScheduledFlightNotFoundException;
	
	public List<ScheduledFlight> searchScheduledFlight(String srcAirport,String dstnAirport,String deptDate);
}
