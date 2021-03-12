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

	@MockBean
	private FlightDAO flightDao;

	@MockBean
	private IScheduleFlightDAO scheduleFDao;

	@MockBean
	private IAirportDAO airportDao;

	@Autowired
	private IScheduledFlightService scheduleFService;

	@Test
	public void testaddScheduledFlight() {
		
		Airport a=new Airport("IDA1RGIA", "RGIA", "Hyderabad");
		when(airportDao.save(a)).thenReturn(a);
	
		Airport a1=new Airport("IDA1KGIA", "KGIA", "Bengaluru");
		when(airportDao.save(a1)).thenReturn(a1);
		
		Flight f =new Flight(555002, "GoJet Airlines", "GJ200", 60);
		when(flightDao.save(f)).thenReturn(f);
		
		ScheduledFlight sFlight = new ScheduledFlight(777001,f.getFlightNumber(), 60,  new Schedule(999001,a.getAirportCode(),a1.getAirportCode(),
				"06-12-2021", "06-12-2021", "10:00", "12:00"), 500.0);
		when(scheduleFDao.save(sFlight)).thenReturn(sFlight);	
			
		System.out.println(sFlight.getScheduleFlightId());
		assertNotNull(sFlight);
//		assertEquals(sFlight1, scheduleFService.addScheduledFlight(sFlight1));

	}

	@Test
	public void testViewAllScheduledFlights()
	{
		Airport a=new Airport("IDA1RGIA", "RGIA", "Hyderabad");
		when(airportDao.save(a)).thenReturn(a);
	
		Airport a1=new Airport("IDA1KGIA", "KGIA", "Bengaluru");
		when(airportDao.save(a1)).thenReturn(a1);
		
		Flight f =new Flight(555002, "GoJet Airlines", "GJ200", 60);
		when(flightDao.save(f)).thenReturn(f);
		
		ScheduledFlight sFlight = new ScheduledFlight(777001,f.getFlightNumber(), 60,  new Schedule(999001,a.getAirportCode(),a1.getAirportCode(),
				"06-12-2021", "06-12-2021", "10:00", "12:00"), 500.0);
		when(scheduleFDao.save(sFlight)).thenReturn(sFlight);	
		List<ScheduledFlight> list=scheduleFDao.findAll();
		assertEquals(list,scheduleFService.viewAllScheduledFlights());
	}
	
	@Test
	public void testViewScheduledFlight()
	{

	}

	
}
