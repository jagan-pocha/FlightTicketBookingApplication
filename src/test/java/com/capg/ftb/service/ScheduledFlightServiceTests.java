package com.capg.ftb.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
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
		
		BigInteger bI=new BigInteger("555020");
		Airport a=new Airport("testA1", "RGIA", "Hyderabad");
		airportDao.save(a);
	
		Airport a1=new Airport("testA2", "KGIA", "Bengaluru");
		airportDao.save(a1);
		
		Flight flight=new Flight(bI,"GoJet Airlines", "GJ200", 60);
		
		Schedule schedule=new Schedule(new BigInteger("999020"),"testA1","testA2","03-12-2021","03-13-2021","10:00","12:00");
		
		ScheduledFlight sFlight=new ScheduledFlight(bI,flight,60,schedule,500.0);
		ScheduledFlight r1=scheduleFDao.save(sFlight);

     	ScheduledFlight r2=scheduleFService.addScheduledFlight(sFlight);
//		System.out.println(test.getScheduleFlightId());
//		System.out.println(test1.getScheduleFlightId());
		assertEquals(r1,r2);

	}

	@Test
	public void testViewAllScheduledFlights()
	{
		BigInteger bI=new BigInteger("1");
		Airport a=new Airport("IDA1RGIA", "RGIA", "Hyderabad");
		//when(airportDao.save(a)).thenReturn(a);
	
		Airport a1=new Airport("IDA1KGIA", "KGIA", "Bengaluru");
	//	when(airportDao.save(a1)).thenReturn(a1);
		
		Flight f =new Flight(bI, "GoJet Airlines", "GJ200", 60);
		//when(flightDao.save(f)).thenReturn(f);
		
		
	
	}
	
	@Test
	public void testViewScheduledFlight()
	{

	}

	
}
