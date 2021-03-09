//Author-S.N.V.Manasvi
package com.capg.ftb.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.capg.ftb.model.Airport;

@Repository
public interface AirportDAO extends CrudRepository<Airport, String> {

}
