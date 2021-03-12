package com.capg.ftb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Validated
public class FlightTicketBookingAppApplication {

	//private final static Logger log = LogManager.getLogger(FlightTicketBookingAppApplication.class);
	public static void main(String[] args) {
	//	log.info(" Application Started");
		SpringApplication.run(FlightTicketBookingAppApplication.class, args);
	}

}
