package com.capg.ftb.service;

import static org.junit.Assert.assertEquals;
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

import com.capg.ftb.dao.FlightDAO;
import com.capg.ftb.model.Flight;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightServiceTests {

	@MockBean
	private FlightDAO flightDao;
	
	@Autowired
	private IFlightService flightService;
	
		
	@Test
	public void testAddFlight()
	{
		BigInteger bI=new BigInteger("1");
		Flight flight=new Flight(bI,"GoJet Airlines","CRJ500",60);
		when(flightDao.save(flight)).thenReturn(flight);
		Flight flight1=flightDao.save(flight);
		assertEquals(flight1,flightService.addFlight(flight1));
	}
	
	
	@Test
	public void test2AddFlight()
	{
		BigInteger bI=new BigInteger("1");
		Flight flight=new Flight(bI,"American Airlines","AAB600",80);
		Flight flight1=flightDao.save(flight);;
		when(flightDao.save(flight1)).thenReturn(flight1);
		assertEquals(flight1,flightService.addFlight(flight1));
	}
	
	
	@Test
	public void testViewFlight() {
		BigInteger bI=new BigInteger("1");
		Optional<Flight> flight = Optional.ofNullable(new Flight(bI,"American Airlines","AAB600",80));
		when(flightDao.save(flight.get())).thenReturn(flight.get());
		when(flightDao.findById(bI)).thenReturn(flight);
		
		}
	
	
	@Test
	public void testViewAllFlights()
	{
		BigInteger bI=new BigInteger("1");
		Flight flight=new Flight(bI,"American Airlines","AAB600",80);
		Flight flight1=new Flight(bI,"Airlines","A600",70);
		flightDao.save(flight);
		flightDao.save(flight1);
		List<Flight> flightsList=flightDao.findAll();
		assertEquals(flightsList,flightService.viewAllFlights());
	}
	
	
	@Test
	public void testDeleteFlight()
	{
		BigInteger bI=new BigInteger("1");
		Flight flight=new Flight(bI,"GoJet Airlines","CRJ500",60);
		when(flightDao.save(flight)).thenReturn(flight);
		flightDao.save(flight);
		flightDao.deleteById(flight.getFlightNumber());
		assertEquals(Optional.empty(),flightDao.findById(bI));
	}
	
	
	@Test
	public void testUpdateFlight()
	{
		BigInteger bI=new BigInteger("1");
		Flight flight=new Flight(bI,"GoJet Airlines","CRJ500",60);
		when(flightDao.save(flight)).thenReturn(flight);
		Flight flight1=flightDao.save(flight);
		flight1.setCarrierName("JetAirways");
		//System.out.print(flight1.getCarrierName());
		assertEquals(flight1,flightService.addFlight(flight1));
	    
	}
	
}
