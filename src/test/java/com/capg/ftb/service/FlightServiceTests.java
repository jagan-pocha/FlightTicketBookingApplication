package com.capg.ftb.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
		BigInteger bigInteger = new BigInteger ("4");
		Flight flight=new Flight(bigInteger,"GoJet Airlines","CRJ500",60);
		when(flightDao.save(flight)).thenReturn(flight);
		assertEquals(flight,flightService.addFlight(flight));
	}
	
	
	@Test
	public void test2AddFlight()
	{
		BigInteger bigInteger = new BigInteger ("4");

		Flight flight=new Flight(bigInteger,"American Airlines","AAB600",80);
		when(flightDao.save(flight)).thenReturn(flight);
		assertEquals(flight,flightService.addFlight(flight));
	}
	
	
	@Test
	public void testViewFlight()
	{
		BigInteger bigInteger = new BigInteger ("4");
		Flight flight=new Flight(bigInteger,"American Airlines","AAB600",80);
		when(flightDao.save(flight)).thenReturn(flight); 
		assertEquals(flight.getFlightModel(),flightService.viewFlight(bigInteger).getFlightModel());
		
	}
	
	
	@Test
	public void testViewAllFlights()
	{
		List<Flight> flightsList=flightDao.findAll();
		assertEquals(flightsList,flightService.viewAllFlights());
	}
	
	
	@Test
	public void testDeleteFlight()
	{

		BigInteger bigInteger = new BigInteger ("4");
		Flight flight=new Flight(bigInteger,"GoJet Airlines","CRJ500",60);
		Mockito.when(flightDao.save(flight)).thenReturn(flight);
		Flight flight1=flightService.removeFlight(bigInteger);
		System.out.println(flight1.getCarrierName());
		assertEquals(flight,flight1);
	}
	
	
	@Test
	public void testUpdateFlight()
	{
		BigInteger bigInteger = new BigInteger ("4");
		Flight flight=new Flight(bigInteger,"GoJet Airlines","CRJ500",60);
		when(flightDao.save(flight)).thenReturn(flight);
	    
	}
	
	
}
