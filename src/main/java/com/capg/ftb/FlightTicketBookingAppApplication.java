package com.capg.ftb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
@Configuration
public class FlightTicketBookingAppApplication {

	//private final static Logger log = LogManager.getLogger(FlightTicketBookingAppApplication.class);
	public static void main(String[] args) {
	//	log.info(" Application Started");
		SpringApplication.run(FlightTicketBookingAppApplication.class, args);
	}

}
