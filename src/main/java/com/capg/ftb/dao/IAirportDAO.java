//Author-S.N.V.Manasvi
package com.capg.ftb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.capg.ftb.model.Airport;
import com.capg.ftb.model.Flight;

@Repository
public interface IAirportDAO extends JpaRepository<Airport, String> {

	

}
