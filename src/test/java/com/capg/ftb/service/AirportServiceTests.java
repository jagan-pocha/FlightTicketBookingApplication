/*
 * Author-S.N.V.Manasvi
 */
package com.capg.ftb.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.capg.ftb.dao.IAirportDAO;
import com.capg.ftb.model.Airport;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AirportServiceTests {

	
	@MockBean
	private IAirportDAO airportDao;
	
	@Autowired
	private IAirportService airportService;

	
	// Test method to test functionality of Add Airport Method.
	@Test
	public void testAddAirport() {
		Airport airport=new Airport("1", "Kempegowda", "Bangalore");
		when(airportDao.save(airport)).thenReturn(airport);
		
		assertEquals(airport,airportService.addAirport(airport));
	}
	
	// Test method to test functionality of view Airport by code Method.
	@Test
	public void testViewAirport() {
		Airport airport = new Airport("1", "Kempegowda", "Bangalore");
		when(airportDao.save(airport)).thenReturn(airport);
		Airport airport1=airportDao.save(airport);
		Airport airport2= airportService.viewAirport(airport.getAirportCode());
		assertEquals(airport1,airport2);
		
	}

	// Test method to test functionality of View all Airports Method.
	@Test
	public void TestListAllAirports() {
		Airport airport = new Airport("2", "Kempegowda", "Bangalore");
		List<Airport> AirportList = airportDao.findAll();
		assertEquals(AirportList, airportService.viewAllAirport());
	}

}
