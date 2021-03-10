package com.capg.ftb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class FlightTicketBookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightTicketBookingAppApplication.class, args);
	}

}
