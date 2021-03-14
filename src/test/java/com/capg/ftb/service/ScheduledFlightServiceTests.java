package com.capg.ftb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.capg.ftb.dao.IAirportDAO;
import com.capg.ftb.dao.FlightDAO;
import com.capg.ftb.dao.IScheduleFlightDAO;
import com.capg.ftb.exception.FlightNotFoundException;
import com.capg.ftb.model.Airport;
import com.capg.ftb.model.Flight;
import com.capg.ftb.model.Schedule;
import com.capg.ftb.model.ScheduledFlight;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduledFlightServiceTests {

	@Autowired
	private FlightDAO flightDao;

	@Autowired
	private IScheduleFlightDAO scheduleFDao;

	@Autowired
	private IAirportDAO airportDao;
	

	@Autowired
	private IScheduledFlightService scheduleFService;

	@Test
	public void testaddScheduledFlight() {
		
		BigInteger bI=new BigInteger("555030");
		Airport a=new Airport("testA1", "RGIA", "Hyderabad");
		airportDao.save(a);
	
		Airport a1=new Airport("testA2", "KGIA", "Bengaluru");
		airportDao.save(a1);
		
		Flight flight=new Flight(bI,"GoJet Airlines", "GJ200", 60);
		
		Schedule schedule=new Schedule(new BigInteger("020"),"testA1","testA2","03-12-2021","03-13-2021","10:00","12:00");
		
		ScheduledFlight sFlight=new ScheduledFlight(new BigInteger("999030"),flight,60,schedule,500.0);
     	ScheduledFlight r2=scheduleFService.addScheduledFlight(sFlight);
		assertEquals(sFlight.getScheduleFlightId(),r2.getScheduleFlightId());
		
		
	}
	
	@Test
	public void testViewScheduledFlight()
	{
		BigInteger bI=new BigInteger("555031");
		Airport a=new Airport("testA1", "RGIA", "Hyderabad");
		airportDao.save(a);
	
		Airport a1=new Airport("testA2", "KGIA", "Bengaluru");
		airportDao.save(a1);
		
		Flight flight=new Flight(bI,"GoJet Airlines", "GJ200", 60);
		
		Schedule schedule=new Schedule(new BigInteger("020"),"testA1","testA2","03-12-2021","03-13-2021","10:00","12:00");
		
		ScheduledFlight sFlight=new ScheduledFlight(new BigInteger("999031"),flight,60,schedule,500.0);
		ScheduledFlight r2=scheduleFService.addScheduledFlight(sFlight);
		ScheduledFlight sFlight1=scheduleFService.viewScheduledFlight(new BigInteger("999031"));
		assertEquals(sFlight.getScheduleFlightId(),sFlight1.getScheduleFlightId());
		
		
	}

	
	@Test
	public void testViewAllScheduledFlights1()
	{
		List<ScheduledFlight> list=scheduleFService.viewAllScheduledFlights();
		assertEquals(scheduleFDao.findAll().size(),list.size());
	}
	
	
	@Test
	public void testModifySFlight()
	{
		BigInteger bI=new BigInteger("555032");
		Airport a=new Airport("testA1", "RGIA", "Hyderabad");
		airportDao.save(a);
	
		Airport a1=new Airport("testA2", "KGIA", "Bengaluru");
		airportDao.save(a1);
		
		Flight flight=new Flight(bI,"GoJet Airlines", "GJ200", 60);
		
		Schedule schedule=new Schedule(new BigInteger("020"),"testA1","testA2","03-12-2021","03-13-2021","10:00","12:00");
		
		ScheduledFlight sFlight=new ScheduledFlight(new BigInteger("999032"),flight,60,schedule,500.0);
		ScheduledFlight r2=scheduleFService.addScheduledFlight(sFlight);
		r2.setAvailableSeats(100);
		r2.getFlight().setSeatCapacity(100);;
		ScheduledFlight sFlight1=scheduleFService.modifyScheduledFlight(r2,new BigInteger("999032"));
		assertEquals(100,sFlight1.getAvailableSeats());
	}
	
	
	
	@Test
	public void testRemoveScheduledFlihgt()
	{
		
		BigInteger bI=new BigInteger("555034");
		Airport a=new Airport("testA1", "RGIA", "Hyderabad");
		airportDao.save(a);
	
		Airport a1=new Airport("testA2", "KGIA", "Bengaluru");
		airportDao.save(a1);
		
		Flight flight=new Flight(bI,"GoJet Airlines", "GJ200", 60);
		
		Schedule schedule=new Schedule(new BigInteger("020"),"testA1","testA2","03-12-2021","03-13-2021","10:00","12:00");
		
		ScheduledFlight sFlight=new ScheduledFlight(new BigInteger("999034"),flight,60,schedule,500.0);
		ScheduledFlight r2=scheduleFService.addScheduledFlight(sFlight);
		scheduleFService.removeScheduledFlight(new BigInteger("999034"));
		assertEquals(2,scheduleFService.viewAllScheduledFlights().size());
	}
	
	@AfterAll
	@Test
	public void testAfterAll()
	{
		scheduleFDao.deleteAll();
		
		flightDao.deleteAll();
		
		airportDao.deleteAll();
		
	}

	
}
