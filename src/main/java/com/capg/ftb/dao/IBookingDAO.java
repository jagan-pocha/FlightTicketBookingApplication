/*
 * Aithor :Thiracy Mary
 */

package com.capg.ftb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.ftb.model.Booking;

@Repository
public interface IBookingDAO extends JpaRepository<Booking,Integer>{

}
