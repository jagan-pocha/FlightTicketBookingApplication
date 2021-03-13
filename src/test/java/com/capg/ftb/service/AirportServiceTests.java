/*
 * Author-S.N.V.Manasvi
 */
package com.capg.ftb.service;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

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

	

	@Autowired
	private IAirportService airportService;

	@MockBean
	private IAirportDAO airportdao;

	// Test method to test functionality of Add Airport Method.
	@Test
	public void testAddAirport() {
		Airport airport = new Airport("001", "Bangalore", "Kempegowda");
		when(airportdao.save(airport)).thenReturn(airport);
		assertEquals(airport, airportService.addAirport(airport));

	}

	// Test method to test functionality of view Airport by code Method.
	@Test
	public void testViewAirport() {
		Optional<Airport> airport1 = Optional.ofNullable(new Airport("001", "Bangalore", "Kempegowda"));
		when(airportdao.save(airport1.get())).thenReturn(airport1.get());
		when(airportdao.findById("001")).thenReturn(airport1);
		assertEquals(airport1,airportdao.findById("001"));
	}

	// Test method to test functionality of View all Airports Method.
	@Test
	public void TestListAllAirports() {
		Airport airport = new Airport("001", "Bangalore", "Kempegowda");
		List<Airport> AirportList = airportdao.findAll();
		assertEquals(AirportList, airportService.viewAllAirport());
	}
}
