/*
 * Jagan mohan
 */

package com.capg.ftb.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.IAirportDAO;
import com.capg.ftb.dao.FlightDAO;
import com.capg.ftb.dao.IScheduleFlightDAO;
import com.capg.ftb.exception.AirportNotFoundException;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.exception.SeatsNotAvailableException;
import com.capg.ftb.model.Airport;
import com.capg.ftb.model.Flight;
import com.capg.ftb.model.ScheduledFlight;

@Service
public class ScheduledFlightServiceImpl implements IScheduledFlightService{

	@Autowired
	private IScheduleFlightDAO scheduleFilghtDao;
	
	@Autowired
	private IAirportDAO airportDao;
	
	@Autowired
	private FlightDAO flightDao;
	
	//Mathod to add Scheduled a flight
	
	@Override
	public ScheduledFlight addScheduledFlight(ScheduledFlight scheduledFlight) {
		// TODO Auto-generated method stub
		
		//Condition to check source airport is available or not 
		
		Optional<Airport> optional=airportDao.findById(scheduledFlight.getSchedule().getSrcAirport());
		//System.out.println(scheduledFlight.getSchedule().getSrcAirport());
		Airport airport=optional.orElseThrow(() ->new AirportNotFoundException("Source Airport Not Existed with the Code: "+scheduledFlight.getSchedule().getSrcAirport()));
		
		//Condition to check destination airport is available or not 
		
		Optional<Airport> optional1=airportDao.findById(scheduledFlight.getSchedule().getDstnAirport());
		Airport airport1=optional1.orElseThrow(()->new AirportNotFoundException("Destination Airport Not Existed with the Code: "+scheduledFlight.getSchedule().getDstnAirport()));

		//Condition to check flight is available or not 
		
		Optional<Flight> optional2=flightDao.findById(scheduledFlight.getFlight());
		Flight flight=optional2.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the Id : "+scheduledFlight.getFlight()));

		//Condition to check seating capacity 
		
		if(flight.getSeatCapacity()!=scheduledFlight.getAvailableSeats())
		{
			throw new SeatsNotAvailableException("Available seats should be equal to flight eating capacity");
		}
		else
		{
		List<ScheduledFlight> list=scheduleFilghtDao.findAll();
		int flag=1;
		for(int i=0;i<list.size();i++)
		{
			
			ScheduledFlight sdFlight=list.get(i);
			if(sdFlight.getFlight()==scheduledFlight.getFlight())
			{
				//Check whether the flight is available to schedule on this day
				
				if(isItScheduled(scheduledFlight.getSchedule().getArrDate(),sdFlight.getSchedule().getArrDate()))
				{
					flag=1;
					
				}
				else
				{
					flag=0;
					break;
				}
			}
		}
		if(flag==0)
		{
			throw new FlightNotFoundException("Flight is Not available to schedule on this date");
		}
		else
		{
		ScheduledFlight scheduledFlight1=scheduleFilghtDao.save(scheduledFlight);
		
		return scheduledFlight1;
		}
		}
	}
	
	//Method to check flight is already scheduled
	
	public boolean isItScheduled(String str,String str1)
	{
		SimpleDateFormat df=new SimpleDateFormat("MM-dd-yyyy");
		Date date1=null;
		try {
			date1 = df.parse(str);
		} catch (Exception e) {
		
			e.printStackTrace();
		} 
		Date date2=null;
		try {
			date2 = df.parse(str1);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		Date date = new Date();   
		if(date1.compareTo(date2)>0 && date1.compareTo(date)>0)
		{
		return true;
		}
		else
		{
			return false;
		}
	}
	
	//Method to modify the scheduled flight

	@Override
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight,int scheduledFlightId) {
		// TODO Auto-generated method stub
		
		//Checking whether the flight is scheduled before to modify
		Optional<ScheduledFlight> optional=scheduleFilghtDao.findById(scheduledFlightId);
		ScheduledFlight sFlight=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+scheduledFlightId));
		
		//checking whether the flight is booked by any pasengers 
		Optional<Flight> optional1=flightDao.findById(sFlight.getFlight());
		Flight flight=optional1.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the Id : "+scheduledFlight.getFlight()));
		
		Optional<Flight> optional2=flightDao.findById(scheduledFlight.getFlight());
		Flight flight1=optional1.orElseThrow(()->new FlightNotFoundException("Flight Not Existed with the Id to schdule : "+scheduledFlight.getFlight()));
		
		if(sFlight.getAvailableSeats()<flight.getSeatCapacity())
		{
			throw new FlightNotFoundException("Unable to modify ! Pasengers are already booked the flight");
		}
		else if(flight1.getSeatCapacity()<scheduledFlight.getAvailableSeats())
		{
			
			throw new FlightNotFoundException("Avialable seats cannot be  more than capacity");
		}
		else
		{
			ScheduledFlight sFlight1=scheduledFlight;
			scheduleFilghtDao.deleteById(scheduledFlightId);
		return scheduledFlight;
		}
	}

	//method to View all scheduled flights
	
	@Override
	public List<ScheduledFlight> viewAllScheduledFlights() {
		// TODO Auto-generated method stub
		List<ScheduledFlight> allSFlights=scheduleFilghtDao.findAll();
		return allSFlights;
	}

	///method to View a scheduled flight based on id
	
	@Override
	public ScheduledFlight viewScheduledFlight(int scheduledFlightId) {
		// TODO Auto-generated method stub
		
		Optional<ScheduledFlight> optional=scheduleFilghtDao.findById(scheduledFlightId);
		ScheduledFlight sFlight=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+scheduledFlightId));
		return sFlight;
	}

	//method to remove the scheduled flight

	@Override
	public void removeScheduledFlight(int scheduleFlightId) {
		// TODO Auto-generated method stub
		Optional<ScheduledFlight> optional=scheduleFilghtDao.findById(scheduleFlightId);
		ScheduledFlight sFlight=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+scheduleFlightId));
		scheduleFilghtDao.deleteById(scheduleFlightId);
		
	}

	//list the scheduled flight based on source and destination and departure date

	@Override
	public List<ScheduledFlight> searchScheduledFlight(String srcAirport, String destAirport,String deptDate) {
		// TODO Auto-generated method stub
		List<ScheduledFlight> list=scheduleFilghtDao.findAll();
		List<ScheduledFlight> list1=new ArrayList<ScheduledFlight>();
		for(int i=0;i<list.size();i++)
		{
			ScheduledFlight sFlight=list.get(i);
			System.out.println(sFlight.getSchedule().getSrcAirport()+" \n"+sFlight.getSchedule().getDstnAirport()+" "+sFlight.getSchedule().getDeptDate());
			if(sFlight.getSchedule().getSrcAirport().equals(srcAirport) && sFlight.getSchedule().getDstnAirport().equals(destAirport) && sFlight.getSchedule().getDeptDate().equals(deptDate))
			{
				list1.add(sFlight);
			}
		}
		return list1;
	}
	
		
	
}
