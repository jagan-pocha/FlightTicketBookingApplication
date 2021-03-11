/*
 * Author Mrudhula
 */

package com.capg.ftb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.ftb.model.Flight;

@Repository
public interface FlightDAO extends JpaRepository<Flight,Integer>{

	
}
