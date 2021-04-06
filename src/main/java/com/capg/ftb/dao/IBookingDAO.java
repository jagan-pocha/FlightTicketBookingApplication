/*
 * Aithor :Thiracy Mary
 */

package com.capg.ftb.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.ftb.model.Booking;

@Repository
public interface IBookingDAO extends JpaRepository<Booking,BigInteger>{

	public List<Booking> getByUserName(String userName);
}
