/*
 * Jagan mohan
 */

package com.capg.ftb.service;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.stereotype.Service;

import com.capg.ftb.dao.IAirportDAO;
import com.capg.ftb.dao.FlightDAO;
import com.capg.ftb.dao.IScheduleFlightDAO;
import com.capg.ftb.exception.AirportNotFoundException;
import com.capg.ftb.exception.FlightExceptions;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.exception.RecordAlreadyPresentException;
import com.capg.ftb.exception.SeatsNotAvailableException;
import com.capg.ftb.exception.scheduleEntityExceptions;
import com.capg.ftb.model.Airport;
import com.capg.ftb.model.Flight;
import com.capg.ftb.model.Schedule;
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

		//validating Flight and Schedule Entity Attributes

		if(validate(scheduledFlight))
		{
			//Condition to check source airport is available or not 

//			Optional<Airport> optional=airportDao.findById(scheduledFlight.getSchedule().getSrcAirport());
//			Airport airport=optional.orElseThrow(() ->new AirportNotFoundException("Source Airport Not Existed with the Code: "+scheduledFlight.getSchedule().getSrcAirport()));
//
//			//Condition to check destination airport is available or not 
//
//			Optional<Airport> optional1=airportDao.findById(scheduledFlight.getSchedule().getDstnAirport());
//			Airport airport1=optional1.orElseThrow(()->new AirportNotFoundException("Destination Airport Not Existed with the Code: "+scheduledFlight.getSchedule().getDstnAirport()));

			//Condition to check seating capacity 

			if(scheduledFlight.getFlight().getSeatCapacity()!=scheduledFlight.getAvailableSeats())
			{
				throw new SeatsNotAvailableException("Available seats should be equal to flight eating capacity");
			}
			else
			{
//				List<ScheduledFlight> list=scheduleFilghtDao.findAll();
//				for(int i=0;i<list.size();i++)
//				{
//
//
//					ScheduledFlight sdFlight=list.get(i);
//
//					//Validating scheduling Flight Id
//
//					if(sdFlight.getScheduleFlightId().compareTo(scheduledFlight.getScheduleFlightId())==0)
//					{
//						throw new RecordAlreadyPresentException("Scheduled Flight alredy existed with given scheduleFlight id");
//					}
//				}
//				List<Flight> fList=flightDao.findAll();
//				for(int i=0;i<fList.size();i++)
//				{
//					Flight flight=fList.get(0);
//
//					// validating Flight Number
//
//					if(flight.getFlightNumber().compareTo(scheduledFlight.getFlight().getFlightNumber())==0)
//					{
//						throw new RecordAlreadyPresentException("Flight with given flight number is already existed, select other flight number");
//
//					}
//				}



				ScheduledFlight scheduledFlight1=scheduleFilghtDao.save(scheduledFlight);

				return scheduledFlight1;

			}
		}
		else
		{
			return null;
		}
	}



	//Method to verify that the scheduling date cannot be the today's date 

	public boolean isItScheduled(String deptDate,String arrDate)
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=null;
		Date date2=null;
		Date date = new Date();   

		try 
		{
			date1 = df.parse(deptDate);
			date2=df.parse(arrDate);
		} 
		catch (Exception e) 
		{

			e.printStackTrace();
		} 

		//Compare todays  date with departure date 

		if(date1.compareTo(date)>0 && date1.compareTo(date2)>=0)
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
	public ScheduledFlight modifyScheduledFlight(ScheduledFlight scheduledFlight,BigInteger scheduledFlightId) {
		// TODO Auto-generated method stub

		//Checking whether the flight is scheduled before to modify
		Optional<ScheduledFlight> optional=scheduleFilghtDao.findById(scheduledFlightId);
		ScheduledFlight sFlight=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+scheduledFlightId));


		//Checking if passengers are already booked the flight or not 

		if(sFlight.getAvailableSeats()<sFlight.getFlight().getSeatCapacity())
		{
			throw new FlightNotFoundException("Unable to modify ! Pasengers are already booked the flight");
		}

		//Flight details can be modified only in flight services

		Flight f=sFlight.getFlight();
		Flight f1=scheduledFlight.getFlight();

		if(!f.getCarrierName().equals(f1.getCarrierName()) || !f.getFlightModel().equals(f1.getFlightModel()) || !f.getSeatCapacity().equals(f1.getSeatCapacity()) || !f.getFlightNumber().equals(f1.getFlightNumber()))
		{
			throw new FlightExceptions("Flight details cannot be modified from this location, can only modified from flight services");
		}

		//While modifiying Id cannot be modified

		if(!(scheduledFlightId.compareTo(scheduledFlight.getScheduleFlightId())==0))
		{
			throw new FlightNotFoundException("scheduling Flight Id can not be modified");
		}
		else if(scheduledFlight.getFlight().getSeatCapacity()<scheduledFlight.getAvailableSeats())
		{

			throw new FlightNotFoundException("Avialable seats cannot be  more than capacity");
		}
		else
		{
			//validate the modifying Scheduling details
			validate(scheduledFlight);

			

			//save the modified table
			ScheduledFlight sFlight1=scheduleFilghtDao.save(scheduledFlight);
			return sFlight1;
		}
	}




	//method to View all scheduled flights

	@Override
	public List<ScheduledFlight> viewAllScheduledFlights() {
		// TODO Auto-generated method stub
		List<ScheduledFlight> allSFlights=scheduleFilghtDao.findAll();
		if(allSFlights.isEmpty())
		{
			throw new FlightNotFoundException("Be the first to schedule the flight");
		}
		else
		{
		return allSFlights;
		}
	}





	///method to View a scheduled flight based on id

	@Override
	public ScheduledFlight viewScheduledFlight(BigInteger scheduledFlightId) {
		// TODO Auto-generated method stub

		Optional<ScheduledFlight> optional=scheduleFilghtDao.findById(scheduledFlightId);
		ScheduledFlight sFlight=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+scheduledFlightId));
		return sFlight;
	}





	//method to remove the scheduled flight

	@Override
	public void removeScheduledFlight(BigInteger scheduleFlightId) {
		// TODO Auto-generated method stub
		Optional<ScheduledFlight> optional=scheduleFilghtDao.findById(scheduleFlightId);
		ScheduledFlight sFlight=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+scheduleFlightId));

		if(sFlight.getAvailableSeats()<sFlight.getFlight().getSeatCapacity())
		{
			throw new FlightNotFoundException("Unable to delete! Pasengers are already booked the flight");
		}

		scheduleFilghtDao.deleteById(scheduleFlightId);

	}




	//list the scheduled flight based on source and destination and departure date

	@Override
	public List<ScheduledFlight> searchScheduledFlight(String srcAirport, String destAirport,String deptDate) {
		// TODO Auto-generated method stub

		//validating date 
		
		
		Pattern p1=Pattern.compile("[2-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]");
		if(!(p1.matcher(deptDate).find()))
		{
			throw new scheduleEntityExceptions("Date Format is Strict must be YYYY-MM-DD");	
		}
		else
		{
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
			
			if(list1.isEmpty())
			{
				throw new FlightNotFoundException("No Scheduled Flights available");
			}
			else
			{
			return list1;
			}
		}
	}




	//validating all the attributes of schedule and Flight Entity

	@Override
	public boolean validate(ScheduledFlight scheduledFlight) {
		// TODO Auto-generated method stub

		Flight flight=scheduledFlight.getFlight();
		System.out.println(flight.getFlightNumber());
		Schedule schedule=scheduledFlight.getSchedule();
		Pattern p=Pattern.compile("[0-9][0-9]:[0-9][0-9]");
		Pattern p1=Pattern.compile("[2-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		
		
		Optional<Airport> optional=airportDao.findById(scheduledFlight.getSchedule().getSrcAirport());
		Airport airport=optional.orElseThrow(() ->new AirportNotFoundException("Source Airport Not Existed with the Code: "+scheduledFlight.getSchedule().getSrcAirport()));

		//Condition to check destination airport is available or not 

		Optional<Airport> optional1=airportDao.findById(scheduledFlight.getSchedule().getDstnAirport());
		Airport airport1=optional1.orElseThrow(()->new AirportNotFoundException("Destination Airport Not Existed with the Code: "+scheduledFlight.getSchedule().getDstnAirport()));



		if(flight.getCarrierName().length()<3 && flight.getFlightModel().length()<3)
		{
			throw new FlightExceptions("Carrier Name and Flight model cannot be less than 3 characters");
		}
		else if(!(p.matcher(schedule.getArrTime()).find()) && !(p.matcher(schedule.getDeptTime()).find()))
		{
			throw new scheduleEntityExceptions("Time Format is Strict must be HH:MM");	
		}
		else if(flight.getSeatCapacity()<30)
		{
			throw new FlightExceptions("minimum seating capacity 30");
		}
		else if(!(p1.matcher(schedule.getDeptDate()).find()) && !(p1.matcher(schedule.getArrDate()).find())) 
		{
			throw new scheduleEntityExceptions("Date Format is Strict must be YYYY-MM-dd");
		}
		else if(schedule.getDstnAirport().equals(schedule.getSrcAirport()))
		{
			throw new scheduleEntityExceptions("Src and Destination can not be same");
		}
//		else if(flight.getFlightNumber().compareTo(new BigInteger("555000"))<0 || flight.getFlightNumber().compareTo(new BigInteger("555999"))>0)
//		{
//			throw new FlightExceptions("Flight Number must be 555000 to 555999");
//		}
		else if(!isItScheduled(scheduledFlight.getSchedule().getDeptDate(),scheduledFlight.getSchedule().getArrDate()))
		{
			throw new FlightNotFoundException("Flight can not be scheduled on this date and arrival date must be before the departure day");
		}
		else
		{
			return true;
		}	

	}


}
