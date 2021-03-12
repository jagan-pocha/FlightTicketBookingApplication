package com.capg.ftb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
public class FlightTicketBookingAppApplication {

	//private final static Logger log = LogManager.getLogger(FlightTicketBookingAppApplication.class);
	public static void main(String[] args) {
	//	log.info(" Application Started");
		SpringApplication.run(FlightTicketBookingAppApplication.class, args);
	}

}
