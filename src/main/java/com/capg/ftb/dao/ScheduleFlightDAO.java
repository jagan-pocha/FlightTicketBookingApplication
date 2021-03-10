package com.capg.ftb.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.ftb.model.ScheduledFlight;


@Repository
public interface ScheduleFlightDAO extends JpaRepository<ScheduledFlight,Integer>{

	 
}
