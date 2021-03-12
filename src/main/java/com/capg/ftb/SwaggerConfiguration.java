package com.capg.ftb;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
    
    private ApiInfo getApiInfo() {
	    return new ApiInfo(
		    "Flight Ticket Booking Application",
		    "Authors: Mrudhula , Manasvi, Thiracy Mary, SundaraLahari, Mery Jennifer, JaganMohan",
		    "1.0.0",
		    "https://ftb.capg.com",
		    new Contact("Batch 1, Group 3 DCX","https://capgemini.com","ftbapp@gmail.com"),
		    "",
		    "LICENSE URL",
		    Collections.emptyList()
	    );
	}
}