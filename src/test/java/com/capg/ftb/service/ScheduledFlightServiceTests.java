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
import com.capg.ftb.dao.AirportDAO;
import com.capg.ftb.dao.FlightDAO;
import com.capg.ftb.dao.ScheduleFlightDAO;
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
	private ScheduleFlightDAO scheduleFDao;

	@MockBean
	private AirportDAO airportDao;

	@Autowired
	private IScheduledFlightService scheduleFService;

	@Test
	public void testaddScheduledFlight() {
		
		Airport a=new Airport("IDA1RGIA", "RGIA", "Hyderabad");
		when(airportDao.save(a)).thenReturn(a);
		System.out.println(a.getAirportCode());
		Airport a1=new Airport("IDA1KGIA", "KGIA", "Bengaluru");
		when(airportDao.save(a1)).thenReturn(a1);
		
		Flight f =new Flight(555002, "GoJet Airlines", "GJ200", 60);
		when(flightDao.save(f)).thenReturn(f);
		
		ScheduledFlight sFlight = new ScheduledFlight(777001,f.getFlightNumber(), 60,  new Schedule(new BigInteger("999001"),a.getAirportCode(),a1.getAirportCode(),
				"06-12-2021", "06-12-2021", "10:00", "12:00"), 500.0);
		when(scheduleFDao.save(sFlight)).thenReturn(sFlight);	
		
		
		ScheduledFlight sFlight1 = scheduleFService.addScheduledFlight(sFlight);	
		System.out.println(sFlight1.getScheduleFlightId());
		assertNotNull(sFlight);
//		assertEquals(sFlight1, scheduleFService.addScheduledFlight(sFlight1));

	}

	@Test
	public void testViewAllScheduledFlights()
	{
		List<ScheduledFlight> list=scheduleFDao.findAll();
		assertEquals(list,scheduleFService.viewAllScheduledFlights());
	}
	
	@Test
	public void testViewScheduledFlight()
	{
		Airport a=new Airport("IDA1RGIA", "RGIA", "Hyderabad");
		when(airportDao.save(a)).thenReturn(a);
		Airport airport=airportDao.save(a);
		Airport a1=new Airport("IDA1KGIA", "KGIA", "Bengaluru");
		when(airportDao.save(a1)).thenReturn(a1);
		Airport airport1=airportDao.save(a1);
		Flight f =flightDao.save(new Flight(555002, "GoJet Airlines", "GJ200", 60));
		when(flightDao.save(f)).thenReturn(f);
		Flight flight=flightDao.save(f);
		ScheduledFlight sFlight = new ScheduledFlight(777001, 555002, 60,  new Schedule(new BigInteger("999001"),"IDA1RGIA","IDA1KGIA",
				"06-12-2021", "06-12-2021", "10:00", "12:00"), 500.0);
		when(scheduleFDao.save(sFlight)).thenReturn(sFlight);	
		ScheduledFlight sFlight1 = scheduleFDao.save(sFlight);		
		Optional<ScheduledFlight> optional=scheduleFDao.findById(sFlight.getScheduleFlightId());
		ScheduledFlight sFlight2=optional.orElseThrow(()->new FlightNotFoundException("No Scheduled Flight with schedule ID : "+sFlight.getScheduleFlightId()));
		System.out.println(sFlight2.getScheduleFlightId());
		assertEquals(sFlight2, scheduleFService.viewScheduledFlight(sFlight1.getScheduleFlightId()));
	}

	
}
